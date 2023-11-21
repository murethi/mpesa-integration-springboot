package com.example.mpesaintegrationspringboot.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MpesaPaymentStatus {
    VALIDATED("VALIDATED"),
    CONFIRMED("CONFIRMED");

    public final String value;


}
