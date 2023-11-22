package com.example.mpesaintegrationspringboot.http;


import com.example.mpesaintegrationspringboot.dto.RegisterUrlRequest;
import com.example.mpesaintegrationspringboot.dto.RegisterUrlResponse;
import com.example.mpesaintegrationspringboot.dto.StkPushPayload;
import com.example.mpesaintegrationspringboot.dto.StkPushResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface MpesaClient {
    @PostExchange("/mpesa/c2b/v1/registerurl")
    RegisterUrlResponse registerUrl(@RequestBody RegisterUrlRequest registerUrlRequest);
    @PostExchange("/mpesa/stkpush/v1/processrequest")
    StkPushResponse mpesaExpress(@RequestBody StkPushPayload stkPushPayload);
}
