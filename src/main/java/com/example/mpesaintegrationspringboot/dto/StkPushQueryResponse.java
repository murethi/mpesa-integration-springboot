package com.example.mpesaintegrationspringboot.dto;

public record StkPushQueryResponse(int ResponseCode,
                                   String ResponseDescription,
                                   String MerchantRequestID,
                                   String CheckoutRequestID,

                                   String ResultCode,
                                   String ResultDesc) {
}
