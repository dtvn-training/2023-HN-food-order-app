package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.config.ApplicationConfig;
import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import com.dtvn.foodorderbackend.model.entity.ResetPasswordOtp;
import com.dtvn.foodorderbackend.repository.RegisterOtpRepository;
import com.dtvn.foodorderbackend.repository.ResetPasswordOtpRepository;
import com.dtvn.foodorderbackend.ulti.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {

    final RegisterOtpRepository registerOtpRepository;
    final ResetPasswordOtpRepository resetPasswordOtpRepository;
    final EmailService emailService;

    public RegisterOtp generateAndSaveRegisterOTP(String email) {
        RegisterOtp otp = new RegisterOtp(email, System.currentTimeMillis() + ApplicationConfig.EXPIRED_REGISTER_OTP, StringUtil.randomOTP());
        save(otp);
        return otp;
    }

    public ResetPasswordOtp generateAndSaveResetPasswordOTP(String email) {
        ResetPasswordOtp otp = new ResetPasswordOtp(email, System.currentTimeMillis() + ApplicationConfig.EXPIRED_RESET_PASSWORD_OTP, StringUtil.randomOTP());
        save(otp);
        return otp;
    }


    public void sendRegisterOTP(RegisterOtp registerOtp) {
        emailService.send(registerOtp.getEmail(), "OTP for registration Foody app", registerOtp.getOTP());
    }

    public void sendResetPasswordOtp(ResetPasswordOtp otp) {
        emailService.send(otp.getEmail(), "OTP for reset password Foody app", otp.getOtp());
    }

    void save(RegisterOtp registerOtp) {
        registerOtpRepository.save(registerOtp);
    }

    void save(ResetPasswordOtp resetPasswordOtp) {
        resetPasswordOtpRepository.save(resetPasswordOtp);
    }

    public boolean checkRegisterOTP(String email, String OTP) {
        return registerOtpRepository.findByEmailAndOTPAndExpiredGreaterThanEqual(email, OTP, System.currentTimeMillis()).isPresent();
    }

    public boolean checkResetPasswordOtp(String email, String otp) {
        return resetPasswordOtpRepository.findByEmailAndOtpAndExpiredGreaterThanEqual(email, otp, System.currentTimeMillis()).isPresent();
    }

    public void destroyRegisterOTP(String email, String OTP) {
        registerOtpRepository.deleteByEmailAndOTP(email, OTP);
    }

    public void destroyResetPasswordOtp(String email, String otp) {
        resetPasswordOtpRepository.deleteByEmailAndOtp(email, otp);
    }
}
