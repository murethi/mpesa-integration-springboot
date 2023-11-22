package com.example.mpesaintegrationspringboot.http;

import com.example.mpesaintegrationspringboot.dto.AccessToken;
import org.springframework.web.service.annotation.GetExchange;

public interface MpesaAuthClient {
    @GetExchange("/oauth/v1/generate?grant_type=client_credentials")
    AccessToken generateToken();


}
