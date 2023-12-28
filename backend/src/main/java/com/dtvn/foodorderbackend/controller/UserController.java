package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final RestaurantService restaurantService;

    @GetMapping("get_all_restaurant")
    public ResponseEntity<?> getAllRestaurant() {
        return ResponseEntity.ok().body(restaurantService.getRestaurantSelected());
    }

    @GetMapping("/get_detail_restaurant")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam("id") long deliveryId) {
        return ResponseEntity.ok().body(restaurantService.getRestaurantById(deliveryId));
    }

    @PostMapping("/vote_restaurant")
    public ResponseEntity<?> voteRestaurant(@RequestParam("id") long deliveryId) {
        // TODO:
        return null;
    }
}
