package com.dtvn.foodorderbackend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    long id;
    String email;
    String token;
    String role;
}
