package com.example.mpesaintegrationspringboot.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "mpesa_payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MpesaPayment {
    @Id
    @Column(name = "transaction_id",unique = true)
    private String TransID;
    @Column(name = "transaction_type")
    private String TransactionType;
    @Column(name = "transaction_time")
    private String TransTime;
    @Column(name = "transaction_amount")
    private BigDecimal TransAmount;
    @Column(name = "business_short_code")
    private int BusinessShortCode;
    @Column(name = "account_number")
    private String BillRefNumber;
    @Column(name = "invoice_number")
    private String InvoiceNumber;
    @Column(name = "organisation_balance")
    private BigDecimal OrgAccountBalance;
    @Column(name = "third_party_transaction_id")
    private String ThirdPartyTransID;
    @Column(name = "first_name")
    private String FirstName;
    @Column(name = "middle_name")
    private String MiddleName;
    @Column(name = "last_name")
    private String LastName;
    private String status;
}
