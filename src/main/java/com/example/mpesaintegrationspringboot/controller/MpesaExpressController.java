package com.example.mpesaintegrationspringboot.controller;

import com.example.mpesaintegrationspringboot.dto.PaymentRequest;
import com.example.mpesaintegrationspringboot.dto.StkPushResponse;
import com.example.mpesaintegrationspringboot.service.MpesaExpressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
