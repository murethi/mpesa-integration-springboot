package com.example.mpesaintegrationspringboot.dto;

import lombok.Builder;

@Builder
public record ConfirmationValidationResponse(String ResultCode, String ResultDesc) {
}
