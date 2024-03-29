package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> orderCart(@RequestBody List<Long> userCartIds) {
        if (!orderService.queueOrder(userCartIds)) {
            return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Lỗi, không thể đặt món, hãy làm mới trang này");
        }
        return BaseResponse.success("ok");
    }
}
