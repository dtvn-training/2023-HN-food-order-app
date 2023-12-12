package com.dtvn.foodorderbackend.model.request;

import com.dtvn.foodorderbackend.annotation.ValidPassword;
import com.dtvn.foodorderbackend.annotation.ValidUsername;
import lombok.Data;

@Data
public class UserChangePasswordRequest {
    @ValidUsername
    public String username;
    @ValidPassword
    public String oldPassword;
    @ValidPassword
    public String newPassword;
}
