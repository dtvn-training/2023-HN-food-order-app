package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController {
    final DishService dishService;

    @GetMapping()
    public ResponseEntity<?> getUserDishDisplayed() {
        return ResponseEntity.ok().body(dishService.getDishApproved());
    }
}
