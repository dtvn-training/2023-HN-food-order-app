package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.request.UserLoginRequest;
import com.dtvn.foodorderbackend.model.dto.response.AuthResponse;
import com.dtvn.foodorderbackend.model.entity.ResetPasswordOtp;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.repository.ResetPasswordOtpRepository;
import com.dtvn.foodorderbackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    final AuthenticationManager authManager;
    final UserRepository userRepository;
    final JwtService jwtService;
    final PasswordEncoder encoder;
    final Mapper mapper;
    final HttpServletRequest request;
    final OtpService otpService;
    final ResetPasswordOtpRepository resetPasswordOtpRepository;
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse login(@NonNull UserLoginRequest request) {
        // TODO: let AuthenticationManager
//        System.out.println("Before AuthenticationManager do auth");
//        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        System.out.println("If AuthenticationManager auth success, this line will appear");
        User user = userRepository.findUserByEmailAndStatus(request.getEmail(), User.Status.VERIFIED);
        if (user == null || !encoder.matches(request.getPassword(), user.getPassword())) {
            logger.info("failed to load user {}", request);
            return null;
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(user.getId(), user.getUsername(), user.getRole(), token);
    }

    public boolean resetPassword(String email) {

        // logger.info("request is: {}",request);
        // logger.info("user mapped is; {}",user);
        // check user exist
        if (!userRepository.existsByEmail(email)) {
            return false;
        }
        ResetPasswordOtp otp = otpService.generateAndSaveResetPasswordOTP(email);
        otpService.sendResetPasswordOtp(otp);
        resetPasswordOtpRepository.save(otp);
        return true;
    }

}
