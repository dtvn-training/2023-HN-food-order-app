package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.dto.request.RegisterVerifyRequest;
import com.dtvn.foodorderbackend.model.dto.request.UserLoginRequest;
import com.dtvn.foodorderbackend.model.dto.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.model.dto.request.cart.UserResetPasswordRequest;
import com.dtvn.foodorderbackend.model.dto.response.AuthResponse;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.service.AuthService;
import com.dtvn.foodorderbackend.service.OtpService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin()
public class AuthController {
    final UserService userService;
    final AuthenticationManager authenticationManager;
    final AuthService authService;
    final OtpService otpService;
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request) {
        if (request.getEmail() == null || request.getPassword() == null || request.getFullName() == null) {
            return BaseResponse.createError(BAD_REQUEST, "Hãy điền các trường hợp lệ");
        }
        if (userService.checkUserExist(request.getEmail())) {
            return BaseResponse.createError(CONFLICT, "Email đã tồn tại");
        }
        return BaseResponse.success(userService.register(request));
    }

    @PostMapping("/verify-register")
    public ResponseEntity<?> verifyRegister(@RequestBody RegisterVerifyRequest request) {
        if (request.getOtp().isEmpty() || request.getEmail().isEmpty()) {
            return BaseResponse.createError(BAD_REQUEST, "Hãy điền các trường hợp lệ");
        }
        if (otpService.checkRegisterOTP(request.getEmail(), request.getOtp())) {
            userService.verifiedRegister(request.getEmail());
            otpService.destroyRegisterOTP(request.getEmail(), request.getOtp());
            return BaseResponse.success();
        }
        return BaseResponse.createError(NOT_ACCEPTABLE, "mã OTP này đã hết hạn");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        if (authResponse == null) {
            return BaseResponse.createError(UNAUTHORIZED, "lỗi đăng nhập");
        }
        return ResponseEntity.ok().body(authResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") @Email String email) {
        //
        if (authService.resetPassword(email)) {
            return BaseResponse.success("Mã OTP đã được gửi về email, sẽ hết hạn sau 5 phút");
        }
        return BaseResponse.createError(NOT_ACCEPTABLE, "Không thể khôi phục được password,hãy kiểm tra lại");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPasswordByOtp(@RequestBody @Valid UserResetPasswordRequest request) {
        if (userService.changePassword(request)) {
            return BaseResponse.success("BẠN ĐÃ THAY ĐỔI MẬT KHẨU THÀNH CÔNG");
        }
        return BaseResponse.createError(NOT_ACCEPTABLE, "Mã OTP của bạn bị sai");
    }

}
