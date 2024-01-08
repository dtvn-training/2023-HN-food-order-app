package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.UserDTO;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.service.RestaurantService;
import com.dtvn.foodorderbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final RestaurantService restaurantService;
    final UserService userService;
    final HttpServletRequest request;
    final Mapper mapper;

    @GetMapping("/get_detail_restaurant")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam("id") long deliveryId) {
        return ResponseEntity.ok().body(restaurantService.getRestaurantById(deliveryId));
    }

    @GetMapping("/get_restaurant_displayed")
    public ResponseEntity<?> getDisplayedRestaurant() {
        return ResponseEntity.ok().body(restaurantService.getRestaurantSelected());
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        long userId = Integer.parseInt(String.valueOf(this.request.getAttribute("user_id")));
        User user = userService.loadUserById(userId);
        return ResponseEntity.ok().body(mapper.map(user, UserDTO.class));
    }

    @GetMapping("/get_user_approved")
    public ResponseEntity<?> getUserApproved(){
        return ResponseEntity.ok().body(mapper.mapList(userService.getUserApproved(), UserDTO.class));
    }
}
