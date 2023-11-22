package com.example.mpesaintegrationspringboot.dto;

import lombok.Builder;

@Builder
public record RegisterUrlRequest(String ShortCode,String ResponseType,String ConfirmationURL,String ValidationURL) {
}
