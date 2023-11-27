package com.example.mpesaintegrationspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public record InternalDisbursementRequest(String CommandID, BigDecimal Amount,
                                          String PhoneNumber, String Remarks, String Occassion) {
}
