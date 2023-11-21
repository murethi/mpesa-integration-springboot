package com.example.mpesaintegrationspringboot.dto;


import java.math.BigDecimal;

/**
 * Once a customer makes a payment, mpesa will check whether you have enabled payment validation
 * if validation is enabled,
 * @param TransactionType
 * @param TransID
 * @param TransTime
 * @param TransAmount
 * @param BusinessShortCode
 * @param BillRefNumber
 * @param InvoiceNumber
 * @param OrgAccountBalance
 * @param ThirdPartyTransID
 * @param FirstName
 * @param MiddleName
 * @param MiddleName
 */
public record ConfirmationValidationDto(String TransactionType, String TransID, String TransTime,
                                        BigDecimal TransAmount, int BusinessShortCode, String BillRefNumber,
                                        String InvoiceNumber, BigDecimal OrgAccountBalance, String ThirdPartyTransID,
                                        String FirstName,
                                        String MiddleName,
                                        String LastName) {


}