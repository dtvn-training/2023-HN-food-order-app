package com.dtvn.foodorderbackend;

import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.model.entity.DishCategory;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.repository.DishCategoryRepository;
import com.dtvn.foodorderbackend.repository.DishRepository;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import com.dtvn.foodorderbackend.ulti.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class FoodOrderBackendApplication {
    final HttpServletRequest request;
    final ShopeeFoodService shopeeFoodService;

    final DishCategoryRepository dishCategoryRepository;
    final DishRepository dishRepository;
    final RestaurantRepository restaurantRepository;
    Logger logger = LoggerFactory.getLogger(FoodOrderBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderBackendApplication.class, args);
    }

    public static Restaurant restaurantTest = new Restaurant();
    public static DishCategory dishCategoryTest = new DishCategory();
    public static Dish dishTest1 = new Dish();
    public static Dish dishTest2 = new Dish();

    static {
        restaurantTest.setName("asdasd");
        restaurantTest.setDeliveryId(1L);
        dishCategoryTest.setId(2L);
        dishCategoryTest.setRestaurant(restaurantTest);
        dishTest1.setId(3L);
        dishTest1.setCategory(dishCategoryTest);
        dishTest2.setId(4L);
        dishTest2.setCategory(dishCategoryTest);
        dishCategoryTest.setDishList(Collections.singletonList(dishTest1));
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> test(@RequestParam("id") int deliveryId) throws Exception {
        Restaurant restaurant = shopeeFoodService.getRestaurantDishes(deliveryId);
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok().build();
    }

}
