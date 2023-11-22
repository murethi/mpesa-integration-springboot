package com.example.mpesaintegrationspringboot.dto.stkcallback;

public record StkCallback(String MerchantRequestID,String CheckoutRequestID,String ResultCode,String ResultDesc) {
}
