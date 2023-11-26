package com.example.mpesaintegrationspringboot.service;

import com.example.mpesaintegrationspringboot.dto.AccessToken;
import com.example.mpesaintegrationspringboot.http.MpesaAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class MpesaAuthenticationService {
    private final MpesaAuthClient mpesaAuthClient;
    @Value("${app.integrations.mpesa.activeCertificate}")
    private String activeCertificate;

    @Value("${app.integrations.mpesa.initiatorPassword}")
    private String initiatorPassword;
    //this map will be used to store the access token in memory
    HashMap<String, String> tokenData = new HashMap<>();

    public String accessToken() {
        if (tokenData != null && tokenData.containsKey("access_token") && tokenData.containsKey("expires_in")) {
            //the token expires in 1 hour
            // we regenerate it if it has a minute or less before it expires.
            if (LocalDateTime.parse(tokenData.get("expires_in")).isAfter(LocalDateTime.now().plusSeconds(60L))) {
                return tokenData.get("access_token");
            }
        }

        return generateToken();
    }

    private String generateToken() {
        AccessToken accessToken = mpesaAuthClient.generateToken();
        tokenData.put("access_token", accessToken.access_token());
        tokenData.put("expires_in", LocalDateTime.now().plusSeconds(accessToken.expires_in()).toString());
        return accessToken.access_token();
    }

    /**
     * this method
     * @return
     */
    public String bearerString(){
        return "Bearer "+accessToken();
    }


    public String generateSecurityCredential(){
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = initiatorPassword.getBytes();

            Resource resource = new ClassPathResource(activeCertificate);

            FileInputStream fin = new FileInputStream(resource.getFile());
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) cf.generateCertificate(fin);
            PublicKey pk = certificate.getPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, pk);

            byte[] cipherText = cipher.doFinal(input);

            // Convert the resulting encrypted byte array into a string using base64 encoding
            return Base64.getEncoder().encodeToString(cipherText).trim();
        } catch (NoSuchAlgorithmException | CertificateException | InvalidKeyException | NoSuchPaddingException |
                 IllegalBlockSizeException | BadPaddingException | NoSuchProviderException | FileNotFoundException e) {
            throw new RuntimeException("There was an error generating the security credential");
        } catch (IOException e) {
            throw new RuntimeException("There was an error generating the security credential");
        }
    }


}