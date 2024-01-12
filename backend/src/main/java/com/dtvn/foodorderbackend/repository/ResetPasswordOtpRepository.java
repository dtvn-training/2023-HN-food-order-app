package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.ResetPasswordOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ResetPasswordOtpRepository extends JpaRepository<ResetPasswordOtp, String> {
    Optional<ResetPasswordOtp> findByEmailAndOtpAndExpiredGreaterThanEqual(String email, String otp, long max_expired);

    @Transactional
    void deleteByEmailAndOtp(String email, String OTP);
}
