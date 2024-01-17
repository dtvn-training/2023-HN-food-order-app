package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController {
    final DishRepository dishRepository;
   // TODO: get dish
}
