package com.dtvn.foodorderbackend.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterVerifyRequest {
    @Email
    String email;
    @Size(min = 6,max = 6)
    String otp;
}
