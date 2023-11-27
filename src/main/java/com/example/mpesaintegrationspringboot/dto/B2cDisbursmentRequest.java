package com.example.mpesaintegrationspringboot.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record B2cDisbursmentRequest(UUID OriginatorConversationID,
                                    String InitiatorName,
                                    String SecurityCredential,
                                    String CommandID,
                                    BigDecimal Amount,
                                    String PartyA,
                                    String PartyB,
                                    String Remarks,
                                    String QueueTimeOutURL,
                                    String ResultURL,
                                    String Occassion) {
}
