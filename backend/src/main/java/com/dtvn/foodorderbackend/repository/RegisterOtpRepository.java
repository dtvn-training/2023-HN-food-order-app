package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import com.dtvn.foodorderbackend.model.entity.ResetPasswordOtp;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RegisterOtpRepository extends JpaRepository<RegisterOtp,String> {
    Optional<RegisterOtp> findByEmailAndOTPAndExpiredGreaterThanEqual(String email, String otp, long max_expired);

    @Transactional
    void deleteByEmailAndOTP(String email,String OTP);
}

