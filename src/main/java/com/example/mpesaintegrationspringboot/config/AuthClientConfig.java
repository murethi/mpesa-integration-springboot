package com.example.mpesaintegrationspringboot.config;

import com.example.mpesaintegrationspringboot.http.MpesaAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AuthClientConfig {
    @Value("${app.integrations.mpesa.consumer-key}")
    private String accessKey;

    @Value("${app.integrations.mpesa.consumer-secret}")
    private String secretKey;

    @Value("${app.integrations.mpesa.domain}")
    private String domain;
    @Bean
    MpesaAuthClient mpesaAuthClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(domain)
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(accessKey,secretKey))
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory.createClient(MpesaAuthClient.class);
    }
}
