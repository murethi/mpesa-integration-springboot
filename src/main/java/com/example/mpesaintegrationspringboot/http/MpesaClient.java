package com.example.mpesaintegrationspringboot.http;


import com.example.mpesaintegrationspringboot.dto.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface MpesaClient {
    @PostExchange("/mpesa/c2b/v1/registerurl")
    RegisterUrlResponse registerUrl(@RequestBody RegisterUrlRequest registerUrlRequest);
    @PostExchange("/mpesa/stkpush/v1/processrequest")
    StkPushResponse mpesaExpress(@RequestBody StkPushPayload stkPushPayload);

    @PostExchange("/mpesa/stkpushquery/v1/query")
    StkPushQueryResponse mpesaExpressQuery(@RequestBody StkPushQuery stkPushQuery);


    @PostExchange("/mpesa/qrcode/v1/generate")
    QrCodeResponse generateQrCode(@RequestBody QrCodeRequest qrCodeRequest);
}
