package com.example.mpesaintegrationspringboot.repository;

import com.example.mpesaintegrationspringboot.entity.MpesaExpress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MpesaExpressRepository extends JpaRepository<MpesaExpress, UUID> {
}
