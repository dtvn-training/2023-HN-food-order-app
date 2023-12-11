package com.dtvn.foodorderbackend.model.request;


import com.dtvn.foodorderbackend.annotation.ValidFullName;
import com.dtvn.foodorderbackend.annotation.ValidPassword;
import com.dtvn.foodorderbackend.annotation.ValidUsername;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @ValidUsername
    String username;
    @ValidFullName
    String fullName;
    @ValidPassword
    String password;
}
