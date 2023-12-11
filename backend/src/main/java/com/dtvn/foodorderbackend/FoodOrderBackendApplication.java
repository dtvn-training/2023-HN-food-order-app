package com.dtvn.foodorderbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FoodOrderBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderBackendApplication.class, args);
    }

    @GetMapping("/test")
    public String hello(){
        return "hhhhhhhhhhhhhhhhhhh";
    }
}
