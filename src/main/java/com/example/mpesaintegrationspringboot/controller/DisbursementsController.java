package com.example.mpesaintegrationspringboot.controller;

import com.example.mpesaintegrationspringboot.dto.B2cDisbursementResponse;
import com.example.mpesaintegrationspringboot.dto.InternalDisbursementRequest;
import com.example.mpesaintegrationspringboot.service.MpesaDisbursementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/disbursements")
@RequiredArgsConstructor
@RestController
public class DisbursementsController {
    private final MpesaDisbursementService disbursementService;
    @PostMapping("pay-customer")
    @ResponseStatus(HttpStatus.OK)
    B2cDisbursementResponse payCustomer(@RequestBody InternalDisbursementRequest disbursementRequest){
        return disbursementService.payCustomer(disbursementRequest);
    }
}
