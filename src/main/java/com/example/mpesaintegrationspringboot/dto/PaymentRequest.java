package com.example.mpesaintegrationspringboot.dto;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount,
                             String phoneNumber,
                             String accountReference,
                             String transactionDescription,
                             String callbackUrl
) {
}
