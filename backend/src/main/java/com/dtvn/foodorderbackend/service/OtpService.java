package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.config.ApplicationConfig;
import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import com.dtvn.foodorderbackend.repository.RegisterOtpRepository;
import com.dtvn.foodorderbackend.ulti.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {

    final RegisterOtpRepository registerOtpRepository;
    final EmailService emailService;

    public RegisterOtp generateAndSaveRegisterOTP(String email) {
        RegisterOtp otp = new RegisterOtp(email, System.currentTimeMillis() + ApplicationConfig.EXPIRED_REGISTER_OTP, StringUtil.randomOTP());
        save(otp);
        return otp;
    }


    public String sendRegisterOTP(RegisterOtp registerOtp) {
        return emailService.send(registerOtp.getEmail(), "OTP for registration Volunteer app", registerOtp.getOTP());
    }

    void save(RegisterOtp registerOtp) {
        registerOtpRepository.save(registerOtp);
    }

    public boolean checkRegisterOTP(String email, String OTP) {
        return registerOtpRepository.checkRegisterOTP(email, OTP, System.currentTimeMillis());
    }

    public void destroyRegisterOTP(String email, String OTP) {
        registerOtpRepository.deleteByEmailAndOTP(email, OTP);
    }

}
