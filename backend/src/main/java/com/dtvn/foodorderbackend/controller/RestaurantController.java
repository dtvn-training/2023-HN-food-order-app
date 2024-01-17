package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.service.RestaurantService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping()
    public ResponseEntity<?> getRestaurantToday(
            @RequestParam(name = "restaurantName", required = false)String restaurantName)
    {
        return ResponseEntity.ok(restaurantService.getRestaurantToday(restaurantName));
    }
}
