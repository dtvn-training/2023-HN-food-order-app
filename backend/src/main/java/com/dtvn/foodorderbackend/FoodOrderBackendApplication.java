package com.dtvn.foodorderbackend;

import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.repository.DishCategoryRepository;
import com.dtvn.foodorderbackend.repository.DishRepository;
import com.dtvn.foodorderbackend.repository.ItemOrderRepository;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class FoodOrderBackendApplication {
    final HttpServletRequest request;
    final ShopeeFoodService shopeeFoodService;

    final DishCategoryRepository dishCategoryRepository;
    final DishRepository dishRepository;
    final RestaurantRepository restaurantRepository;
    final ItemOrderRepository itemOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderBackendApplication.class, args);
    }

    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> error() {
        return BaseResponse.success("ERROR");
    }

}
