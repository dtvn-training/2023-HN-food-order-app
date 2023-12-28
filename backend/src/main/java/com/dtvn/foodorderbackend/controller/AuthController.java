package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.mapper.BaseMapper;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.RegisterVerifyRequest;
import com.dtvn.foodorderbackend.model.request.UserLoginRequest;
import com.dtvn.foodorderbackend.model.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.model.response.AuthResponse;
import com.dtvn.foodorderbackend.service.AuthService;
import com.dtvn.foodorderbackend.service.OtpService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    final UserService userService;
    final AuthenticationManager authenticationManager;
    final AuthService authService;
    final OtpService otpService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request) {
        if(request.getEmail() == null || request.getPassword() == null || request.getFullName() == null){
            return ResponseEntity.status(BAD_REQUEST).body("You must fill all field");
        }
        if (userService.checkUserExist(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email exist");
        }
        return ResponseEntity.ok().body(userService.register(request));
    }

    @PostMapping("/verify_register")
    public ResponseEntity<?> verifyRegister(@RequestBody RegisterVerifyRequest request) {
        if (request.getOtp().isEmpty() || request.getEmail().isEmpty()) {
            return ResponseEntity.status(BAD_REQUEST).body("All fields must be filled in.");
        }
        if (otpService.checkRegisterOTP(request.getEmail(), request.getOtp())) {
            userService.verifiedRegister(request.getEmail());
            otpService.destroyRegisterOTP(request.getEmail(), request.getOtp());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("OTP was expired or OTP not valid");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        if (authResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().body(authResponse);
    }

}
