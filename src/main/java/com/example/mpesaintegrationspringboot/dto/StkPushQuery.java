package com.example.mpesaintegrationspringboot.dto;

import lombok.Builder;

@Builder
public record StkPushQuery(String BusinessShortCode, String Password, String Timestamp,String CheckoutRequestID) {
}
