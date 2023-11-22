package com.example.mpesaintegrationspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "mpesa_express")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MpesaExpress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "business_short_code")
    private int BusinessShortCode;
    @Column(name = "timestamp")
    private String Timestamp;
    @Column(name = "transaction_type")

    private String TransactionType;
    @Column(name = "amount")
    private String Amount;
    @Column(name = "PartyA")
    private String PartyA;
    @Column(name = "PartyB")
    private String PartyB;
    @Column(name = "phone_number")
    private String PhoneNumber;
    @Column(name = "callback_url")
    private String CallBackURL;
    @Column(name = "account_reference")
    private String AccountReference;
    @Column(name = "transaction_description")
    private String TransactionDesc;
    @Column(name = "checkout_request_id",unique = true)
    private String checkoutRequestId;
    @Column(name = "merchant_request_id",unique = true)
    private String merchantRequestId;
    private int responseCode;
    private String responseDescription;
    private String customerMessage;
    private String resultCode;
    private String resultDescription;

}
