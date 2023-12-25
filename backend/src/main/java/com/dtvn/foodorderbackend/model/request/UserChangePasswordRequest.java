package com.dtvn.foodorderbackend.model.request;

import com.dtvn.foodorderbackend.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserChangePasswordRequest {
    @Email(message = "EMAIL NOT VALID")
    public String email;
    @ValidPassword
    public String oldPassword;
    @ValidPassword
    public String newPassword;
}
