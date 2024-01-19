package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.request.UserChangePasswordRequest;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.model.dto.response.UserDTO;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.service.RestaurantService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dtvn.foodorderbackend.model.dto.response.BaseResponse.createError;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    final RestaurantService restaurantService;
    final UserService userService;
    final HttpServletRequest request;
    final Mapper mapper;

    @GetMapping("/get-detail-restaurant")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam("id") long deliveryId) {
        Restaurant restaurant = restaurantService.getRestaurantById(deliveryId);
        if (restaurant == null) {
            return createError(HttpStatus.NOT_FOUND, "Không có cửa hàng này");
        }
        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping("/get-restaurant-displayed")
    public ResponseEntity<?> getDisplayedRestaurant() {
        return ResponseEntity.ok().body(restaurantService.getRestaurantSelected());
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        long userId = Integer.parseInt(String.valueOf(this.request.getAttribute("user_id")));
        User user = userService.loadUserById(userId);
        return ResponseEntity.ok().body(mapper.map(user, UserDTO.class));
    }

    @GetMapping("/get-user-approved")
    public ResponseEntity<?> getUserApproved() {
        return ResponseEntity.ok().body(mapper.mapList(userService.getUserApproved(), UserDTO.class));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserChangePasswordRequest userChangePasswordRequest) {
        if (userService.changePassword(userChangePasswordRequest)) {
            return BaseResponse.success("Bạn đã thay đổi mật khẩu thành công");
        }
        return createError(HttpStatus.NOT_ACCEPTABLE, "Không thể thay đổi mật khẩu, nguyên nhân có thể do email hoặc mật khẩu cũ không đúng");
    }
}
