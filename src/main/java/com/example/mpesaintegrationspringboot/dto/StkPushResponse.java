package com.example.mpesaintegrationspringboot.dto;

public record StkPushResponse(String MerchantRequestID,String CheckoutRequestID,int ResponseCode,String ResponseDescription,String CustomerMessage) {

}
