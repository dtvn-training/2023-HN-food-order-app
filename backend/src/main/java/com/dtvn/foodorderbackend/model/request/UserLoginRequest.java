package com.dtvn.foodorderbackend.model.request;

import com.dtvn.foodorderbackend.annotation.ValidPassword;
import com.dtvn.foodorderbackend.annotation.ValidUsername;
import lombok.Data;

@Data
public class UserLoginRequest {
    @ValidUsername
    String username;
    @ValidPassword
    String password;
}
