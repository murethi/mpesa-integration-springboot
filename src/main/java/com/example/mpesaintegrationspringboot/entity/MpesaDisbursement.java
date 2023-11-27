package com.example.mpesaintegrationspringboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "mpesa_disbursements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MpesaDisbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "command_id",nullable = false)
    String CommandID;
    @Column(name = "amount",nullable = false)
    BigDecimal Amount;
    @Column(name = "short_code",nullable = false)
    String PartyA;
    @Column(name = "receiving_phone_number",nullable = false)
    String PartyB;
    @Column(name = "remarks",nullable = false)
    String Remarks;
    @Column(name = "occassion",nullable = false)
    String Occassion;
    @Column(name = "conversation_id")
    String ConversationID;
    @Column(name = "originator_conversation_id")
    String OriginatorConversationID;
    @Column(name = "response_code")
    String ResponseCode;
    @Column(name = "response_description")
    String ResponseDescription;
}
