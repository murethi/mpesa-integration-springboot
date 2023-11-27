package com.example.mpesaintegrationspringboot.repository;

import com.example.mpesaintegrationspringboot.entity.MpesaDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MpesaDisbursementRepository extends JpaRepository<MpesaDisbursement, UUID> {
}
