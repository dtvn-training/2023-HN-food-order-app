package com.dtvn.foodorderbackend;

import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.service.ShopeeFoodService;
import com.dtvn.foodorderbackend.ulti.GsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;


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

    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> test(@RequestParam("id") int deliveryId) throws Exception {
        var x = shopeeFoodService.getDishOfRestaurant(deliveryId);
//        String k = GsonUtil.toString(x);
        return ResponseEntity.ok().body(x);
//        return x;
        // need to change content_type to application/json
    }

}
