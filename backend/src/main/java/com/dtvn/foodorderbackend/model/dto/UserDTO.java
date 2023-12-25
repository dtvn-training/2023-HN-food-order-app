package com.dtvn.foodorderbackend.model.dto;

import com.dtvn.foodorderbackend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String fullName;
    String email;
    User.Status status;
    User.Role role;
    Long loan;
}
