package com.dtvn.foodorderbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    int id;

    String username;
    String token;
}
