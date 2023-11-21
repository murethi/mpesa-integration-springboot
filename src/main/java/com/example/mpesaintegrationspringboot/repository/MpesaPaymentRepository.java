package com.example.mpesaintegrationspringboot.repository;


import com.example.mpesaintegrationspringboot.entity.MpesaPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MpesaPaymentRepository extends JpaRepository<MpesaPayment,String> {
}
