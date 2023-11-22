package com.example.mpesaintegrationspringboot.controller;

import com.example.mpesaintegrationspringboot.dto.ConfirmationValidationDto;
import com.example.mpesaintegrationspringboot.dto.ConfirmationValidationResponse;
import com.example.mpesaintegrationspringboot.dto.RegisterUrlRequest;
import com.example.mpesaintegrationspringboot.dto.RegisterUrlResponse;
import com.example.mpesaintegrationspringboot.service.MpesaPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class MpesaPaymentController {
    private final MpesaPaymentService mpesaPaymentService;


    /**
     *  This is the URL that is only used when a Merchant
     *  (Partner) requires to validate the
     *  details of the payment before accepting.
     *  For example, a bank would want to verify if an account number exists
     *  in their platform before accepting a payment from the customer.
     * @param payment
     * @return
     */
    @PostMapping("validate")
    @ResponseStatus(HttpStatus.OK)
    ConfirmationValidationResponse validatePayment(@RequestBody ConfirmationValidationDto payment){
        return mpesaPaymentService.validatePayment(payment);
    }

    /**
     *  This is the URL that receives payment notification once payment has been
     *  completed successfully on M-PESA.
     * @param payment
     * @return
     */
    @PostMapping("confirm")
    @ResponseStatus(HttpStatus.OK)
    ConfirmationValidationResponse confirmPayment(@RequestBody ConfirmationValidationDto payment){
        return mpesaPaymentService.confirmPayment(payment);
    }

    @PostMapping("register-urls")
    @ResponseStatus(HttpStatus.OK)
    RegisterUrlResponse registerUrls(@RequestBody RegisterUrlRequest registerUrlRequest){
        return mpesaPaymentService.registerUrl(registerUrlRequest);
    }

}
