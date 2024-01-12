package com.dtvn.foodorderbackend.model.dto.request.cart;

import com.dtvn.foodorderbackend.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserResetPasswordRequest {
    @Email
    String email;
    @Size(min = 6, max = 6)
    String otp;
    @ValidPassword
    String newPassword;
}
