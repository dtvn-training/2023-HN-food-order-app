package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import com.dtvn.foodorderbackend.model.response.ItemOrderDisplayResponse;
import com.dtvn.foodorderbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {
    final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> adminOrder(@RequestBody List<Integer> orderIds) {
        return null;
    }

    @GetMapping("/get_list_order")
    public ResponseEntity<?> getAllActiveItemOrder() {
        List<ItemOrderDisplayResponse> responses = orderService.getAllItemOrderNotApproved().stream().map(ItemOrder::toDisplayResponse).toList();
        return ResponseEntity.ok().body(responses);
    }
}
