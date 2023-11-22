package com.example.mpesaintegrationspringboot.dto;

import java.math.BigDecimal;

public record QrCodeRequest(String MerchantName, String RefNo, BigDecimal Amount,String TrxCode,String CPI,int Size) {

}
