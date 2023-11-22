package com.example.mpesaintegrationspringboot.service;

import com.example.mpesaintegrationspringboot.dto.PaymentRequest;
import com.example.mpesaintegrationspringboot.dto.StkPushPayload;
import com.example.mpesaintegrationspringboot.dto.StkPushResponse;
import com.example.mpesaintegrationspringboot.entity.MpesaExpress;
import com.example.mpesaintegrationspringboot.http.MpesaClient;
import com.example.mpesaintegrationspringboot.repository.MpesaExpressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class MpesaExpressService {
    private final MpesaClient mpesaClient;
    private final MpesaExpressRepository mpesaExpressRepository;


    @Value("${app.integrations.mpesa.express.shortCode}")
    private String shortCode;
    @Value("${app.integrations.mpesa.passKey}")
    private String passKey;

    public StkPushResponse paymentRequest(PaymentRequest paymentRequest){

        String timestamp = timestamp();
        //generate password
        String password = password(timestamp);
        StkPushPayload stkPushPayload = StkPushPayload.builder()
                .AccountReference(paymentRequest.accountReference())
                .TransactionType("CustomerPayBillOnline")
                .Amount(paymentRequest.amount())
                .PhoneNumber(paymentRequest.phoneNumber())
                .TransactionDesc(paymentRequest.transactionDescription())
                .Password(password)
                .PartyA("254708374149")
                .PartyB(shortCode)
                .CallBackURL(paymentRequest.callbackUrl())
                .Timestamp(timestamp)
                .BusinessShortCode(shortCode)
                .build();
        log.info(stkPushPayload.toString());

        StkPushResponse stkPushResponse = mpesaClient.mpesaExpress(stkPushPayload);

        //we record request on the db
        MpesaExpress model = new MpesaExpress();
        BeanUtils.copyProperties(stkPushPayload,model);
        model.setResponseCode(stkPushResponse.ResponseCode());
        model.setResponseDescription(stkPushResponse.ResponseDescription());
        model.setCustomerMessage(stkPushResponse.CustomerMessage());
        model.setMerchantRequestId(stkPushResponse.MerchantRequestID());
        model.setCheckoutRequestId(stkPushResponse.CheckoutRequestID());

        mpesaExpressRepository.save(model);
        return stkPushResponse;
    }


    private String timestamp(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    private String password(String timestamp){


        return Base64.getEncoder().encodeToString((shortCode + passKey + timestamp).getBytes());

    }
}
