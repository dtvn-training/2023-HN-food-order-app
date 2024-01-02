package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.annotation.ValidFullName;
import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.UserDTO;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.response.SimpleRestaurantResponse;
import com.dtvn.foodorderbackend.service.RestaurantService;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
    final Mapper mapper;
    final ShopeeFoodService shopeeFoodService;
    final RestaurantService restaurantService;
    @SuppressWarnings("unused")
    final Logger logger = LoggerFactory.getLogger(AdminController.class);
    static SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(User.Role.ADMIN.name());

    @GetMapping("/get_users")
    public ResponseEntity<List<UserDTO>> getUserNotVerify(
            @RequestParam(value = "fullName", required = false) @ValidFullName String fullName,
            @RequestParam(value = "email", required = false) @Email(message = "EMAIL NOT VALID") String email,
            @RequestParam(value = "role", required = false) User.Role role,
            @RequestParam(value = "status", required = false) User.Status status
    ) {
        if (userService.loadUserByUsername(String.valueOf(request.getAttribute("email"))).getAuthorities().contains(ADMIN)) {
            return ResponseEntity.ok().body(userService.getUserByCriteria(fullName, email, role, status));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/approved_user")
    public ResponseEntity<?> verifyUserByEmail(
            @RequestParam(value = "email") @Email(message = "EMAIL NOT VALID") String email
    ) {
        if (userService.loadUserByUsername(String.valueOf(request.getAttribute("email"))).getAuthorities().contains(ADMIN)) {
            userService.changeApprovedByEmail(email, true);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/fetch_data")
    public ResponseEntity<?> fetchDataByAdmin(@RequestParam("url") @NonNull String url) throws Exception {
        if (!userService.loadUserByUsername(String.valueOf(request.getAttribute("email"))).getAuthorities().contains(ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (shopeeFoodService.fetchRestaurantData(url)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/get_all_restaurant")
    public ResponseEntity<?> getAllRestaurant() {
        if (userService.loadUserByUsername(String.valueOf(request.getAttribute("email"))).getAuthorities().contains(ADMIN)) {
            List<SimpleRestaurantResponse> restaurants = restaurantService.getAllPresentRestaurant();
            return ResponseEntity.ok().body(restaurants);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/get_user_not_approved")
    public ResponseEntity<?> getUserNotApproved() {
        return ResponseEntity.ok().body(mapper.mapList(userService.getUserNotApproved(), UserDTO.class));
    }

    @GetMapping("/get_user_approved")
    public ResponseEntity<?> getUserApproved() {
        return ResponseEntity.ok().body(mapper.mapList(userService.getUserApproved(), UserDTO.class));
    }

    @PostMapping("/pick_restaurant")
    public ResponseEntity<?> approvedRestaurant() {
        return null;
    }
}
