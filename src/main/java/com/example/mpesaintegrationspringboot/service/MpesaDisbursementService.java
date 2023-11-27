package com.example.mpesaintegrationspringboot.service;

import com.example.mpesaintegrationspringboot.dto.B2cDisbursementResponse;
import com.example.mpesaintegrationspringboot.dto.B2cDisbursmentRequest;
import com.example.mpesaintegrationspringboot.dto.InternalDisbursementRequest;
import com.example.mpesaintegrationspringboot.entity.MpesaDisbursement;
import com.example.mpesaintegrationspringboot.http.MpesaClient;
import com.example.mpesaintegrationspringboot.repository.MpesaDisbursementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MpesaDisbursementService {
    private final MpesaDisbursementRepository mpesaDisbursementRepository;
    private final MpesaAuthenticationService mpesaAuthenticationService;
    private final MpesaClient mpesaClient;

    @Value("${app.integrations.mpesa.initiatorName}")
    private String initiatorName;
    @Value("${app.integrations.mpesa.shortCode}")
    private String shortCode;

    @Value("${app.baseUrl}")
    private String baseUrl;

    public B2cDisbursementResponse payCustomer(InternalDisbursementRequest disbursementRequest){
        String securityCredential = mpesaAuthenticationService.generateSecurityCredential();
        MpesaDisbursement model = saveDisbursement(disbursementRequest);
        B2cDisbursmentRequest b2cDisbursmentRequest = B2cDisbursmentRequest.builder()
                .PartyA(shortCode)
                .PartyB(disbursementRequest.PhoneNumber())
                .CommandID(disbursementRequest.CommandID())
                .Remarks(disbursementRequest.Remarks())
                .Occassion(disbursementRequest.Occassion())
                .Amount(disbursementRequest.Amount())
                .InitiatorName(initiatorName)
                .SecurityCredential(securityCredential)
                .OriginatorConversationID(model.getId())
                .QueueTimeOutURL(baseUrl+"/disbursment/"+model.getId())
                .ResultURL(baseUrl+"/disbursment/timeout/"+model.getId())
                .build();
        log.info(b2cDisbursmentRequest.toString());
        B2cDisbursementResponse disbursementResponse = mpesaClient.b2cPaymentRequest(b2cDisbursmentRequest);

        //proceed to save the response we get from the gateway
        model.setOriginatorConversationID(disbursementResponse.OriginatorConversationID());
        model.setConversationID(disbursementResponse.ConversationID());
        model.setResponseCode(disbursementResponse.ResponseCode());
        model.setResponseCode(disbursementResponse.ResponseCode());
        model.setResponseDescription(disbursementResponse.ResponseDescription());
        mpesaDisbursementRepository.save(model);

        return disbursementResponse;
    }

    private MpesaDisbursement saveDisbursement(InternalDisbursementRequest disbursementRequest){
        MpesaDisbursement model = MpesaDisbursement.builder()
                .PartyA(shortCode)
                .PartyB(disbursementRequest.PhoneNumber())
                .CommandID(disbursementRequest.CommandID())
                .Remarks(disbursementRequest.Remarks())
                .Occassion(disbursementRequest.Occassion())
                .Amount(disbursementRequest.Amount())
                .build();
        return mpesaDisbursementRepository.save(model);
    }
}
