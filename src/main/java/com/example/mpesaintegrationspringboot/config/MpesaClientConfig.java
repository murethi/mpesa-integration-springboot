package com.example.mpesaintegrationspringboot.config;

import com.example.mpesaintegrationspringboot.http.MpesaClient;
import com.example.mpesaintegrationspringboot.service.MpesaAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class MpesaClientConfig {
    private final MpesaAuthenticationService mpesaAuthenticationService;

    @Value("${app.integrations.mpesa.domain}")
    private String domain;
    @Bean
    MpesaClient mpesaClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(domain)
                .defaultHeader("Authorization",mpesaAuthenticationService.bearerString())
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory.createClient(MpesaClient.class);
    }
}
