package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.annotation.ValidFullName;
import com.dtvn.foodorderbackend.annotation.ValidUsername;
import com.dtvn.foodorderbackend.model.dto.UserDTO;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    final UserService userService;
    final HttpServletRequest request;
    final HttpServletResponse response;
    static SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(User.Role.ADMIN.name());

    @GetMapping("/get_users")
    public ResponseEntity<List<UserDTO>> getUserNotVerify(
            @RequestParam(value = "fullName", required = false) @ValidFullName String fullName,
            @RequestParam(value = "username", required = false) @ValidUsername String username,
            @RequestParam(value = "role", required = false) User.Role role,
            @RequestParam(value = "status", required = false) User.Status status
    ) {
        if (userService.loadUserByUsername(String.valueOf(request.getAttribute("username"))).getAuthorities().contains(ADMIN)) {
            return ResponseEntity.ok().body(userService.getUserByCriteria(fullName, username, role, status));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/verify_user")
    public ResponseEntity<?> verifyUserByUsername(
            @RequestParam(value = "username") @ValidUsername String username
    ) {
        if (userService.loadUserByUsername(String.valueOf(request.getAttribute("username"))).getAuthorities().contains(ADMIN)) {
            userService.changeStatusByUsername(username,User.Status.VERIFIED);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
