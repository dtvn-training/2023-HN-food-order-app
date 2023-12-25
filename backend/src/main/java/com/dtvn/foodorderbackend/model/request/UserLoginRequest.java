package com.dtvn.foodorderbackend.model.request;

import com.dtvn.foodorderbackend.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserLoginRequest {
    @Email(message = "EMAIL NOT VALID")
    String email;
    @ValidPassword
    String password;
}
