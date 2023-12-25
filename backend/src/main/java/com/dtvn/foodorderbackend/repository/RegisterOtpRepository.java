package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
public interface RegisterOtpRepository extends JpaRepository<RegisterOtp,String> {
    @Query(value = "select exists(select * from register_otp r where r.email = ?1 AND r.otp = ?2 AND r.expired >= ?3)",nativeQuery = true)
    boolean checkRegisterOTP(String email, String otp, long max_expired);

    @Transactional
    void deleteByEmailAndOTP(String email,String OTP);
}

