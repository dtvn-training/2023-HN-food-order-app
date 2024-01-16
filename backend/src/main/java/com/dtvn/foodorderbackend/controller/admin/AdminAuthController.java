package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.annotation.ValidFullName;
import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.response.UserDTO;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.service.RestaurantService;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import com.dtvn.foodorderbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {
    final UserService userService;
    final HttpServletRequest request;
    final HttpServletResponse response;
    final Mapper mapper;
    final ShopeeFoodService shopeeFoodService;
    final RestaurantService restaurantService;
    final AdminController adminController;
    @SuppressWarnings("unused")
    final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/get-users")
    @Operation
    public ResponseEntity<?> getUserNotVerify(
            @RequestParam(value = "fullName", required = false) @ValidFullName String fullName,
            @RequestParam(value = "email", required = false) @Email(message = "EMAIL NOT VALID") String email,
            @RequestParam(value = "role", required = false) User.Role role,
            @RequestParam(value = "status", required = false) User.Status status
    ) throws Exception {
        return ResponseEntity.ok().body(userService.getUserByCriteria(fullName, email, role, status));
    }

    @PostMapping("/approve-user")
    public ResponseEntity<?> approveUserByEmail(
            @RequestParam(value = "email") @Email(message = "EMAIL NOT VALID") String email
    ) throws Exception {
        userService.changeApprovedByEmail(email, true);
        return BaseResponse.success();
    }

    @PostMapping("/disapprove-user")
    public ResponseEntity<?> disapproveUserByEmail(
            @RequestParam(value = "email") @Email(message = "EMAIL NOT VALID") String email
    ) throws Exception {
        userService.changeApprovedByEmail(email, false);
        return BaseResponse.success();
    }

    @GetMapping("/get-user-not-approved")
    public ResponseEntity<?> getUserNotApproved() throws Exception {
        return ResponseEntity.ok().body(mapper.mapList(userService.getUserNotApproved(), UserDTO.class));
    }


}