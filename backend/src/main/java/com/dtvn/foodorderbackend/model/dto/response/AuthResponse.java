package com.dtvn.foodorderbackend.model.dto.response;

import com.dtvn.foodorderbackend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    long id;
    String email;
    User.Role role;
    String token;
}
