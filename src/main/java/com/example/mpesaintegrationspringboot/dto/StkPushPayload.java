package com.example.mpesaintegrationspringboot.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StkPushPayload(String BusinessShortCode,String Password,String Timestamp,
                            String TransactionType, BigDecimal Amount,
                            String PartyA,String PartyB,String PhoneNumber,
                            String CallBackURL, String AccountReference, String TransactionDesc
                            ) {

}
