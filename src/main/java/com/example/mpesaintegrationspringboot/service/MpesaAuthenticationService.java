package com.example.mpesaintegrationspringboot.service;

import com.example.mpesaintegrationspringboot.dto.AccessToken;
import com.example.mpesaintegrationspringboot.http.MpesaAuthClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class MpesaAuthenticationService {
    private final MpesaAuthClient mpesaAuthClient;
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
}