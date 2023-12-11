package com.dtvn.foodorderbackend.model.dto;

import com.dtvn.foodorderbackend.model.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    String fullName;
    String userName;
    String password;
    User.Role role;
    User.Status status;
    Long loan;
}
