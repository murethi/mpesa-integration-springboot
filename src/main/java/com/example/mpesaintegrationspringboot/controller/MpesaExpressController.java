package com.example.mpesaintegrationspringboot.controller;

import com.example.mpesaintegrationspringboot.dto.PaymentRequest;
import com.example.mpesaintegrationspringboot.dto.StkPushQueryResponse;
import com.example.mpesaintegrationspringboot.dto.StkPushResponse;
import com.example.mpesaintegrationspringboot.dto.stkcallback.StkCallback;
import com.example.mpesaintegrationspringboot.dto.stkcallback.StkPushResultBody;
import com.example.mpesaintegrationspringboot.service.MpesaExpressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/express-payment")
@RequiredArgsConstructor
public class MpesaExpressController {
    private final MpesaExpressService mpesaExpressService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    StkPushResponse paymentRequest(@RequestBody PaymentRequest paymentRequest){
        return mpesaExpressService.paymentRequest(paymentRequest);
    }

    @PostMapping("callback")
    @ResponseStatus(HttpStatus.OK)
    void callback(@RequestBody StkPushResultBody stkPushResultBody){
        mpesaExpressService.stkpushResult(stkPushResultBody);
    }

    @PostMapping("query/{id}")
    @ResponseStatus(HttpStatus.OK)
    StkPushQueryResponse stkPushPaymentQuery(@PathVariable UUID id){
        return mpesaExpressService.stkPushPaymentQuery(id);
    }
}
