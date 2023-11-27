package com.example.mpesaintegrationspringboot.dto.b2cResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record B2cResult (String ResultType, String ResultCode,String ResultDesc,String OriginatorConversationID,String ConversationID,String TransactionID){
}
