package com.dtvn.foodorderbackend.model.dto.request;


import com.dtvn.foodorderbackend.annotation.ValidFullName;
import com.dtvn.foodorderbackend.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @Email(message = "EMAIL NOT VALID")
    String email;
    @ValidFullName
    String fullName;
    @ValidPassword
    String password;
}
