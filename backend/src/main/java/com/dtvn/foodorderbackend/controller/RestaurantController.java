package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    final RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<?> getRestaurantToday(
            @RequestParam(name = "restaurantName", required = false) String restaurantName) {
        // TODO: PENDING
        return ResponseEntity.ok(restaurantService.getRestaurantToday(restaurantName));
    }
}
