package com.dtvn.foodorderbackend;

import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class FoodOrderBackendApplication {
    final HttpServletRequest request;
    final ShopeeFoodService shopeeFoodService;
    Logger logger = LoggerFactory.getLogger(FoodOrderBackendApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FoodOrderBackendApplication.class, args);
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<?> test(@RequestParam("id") int deliveryId) throws Throwable {
        var x = shopeeFoodService.getDishOfRestaurant(deliveryId);
        var y = ResponseEntity.ok().body(x);
        logger.info("data response {}",y.getHeaders().getAccept());
        return y;
    }
}
