package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.request.CartRequest;
import com.dtvn.foodorderbackend.model.response.BaseResponse;
import com.dtvn.foodorderbackend.service.CartService;
import com.dtvn.foodorderbackend.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    final CartService cartService;
    final HttpServletRequest request;
    final OrderService orderService;
    @SuppressWarnings("unused")
    Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("/add_to_cart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest request) {
        if (cartService.addToCart(request, Integer.parseInt(String.valueOf(this.request.getAttribute("user_id"))))) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Cart exist or not valid dish id");
    }

    @PutMapping("/change_quantity")
    public ResponseEntity<?> changeQuantity(@RequestBody @NonNull CartRequest request) {
        if (cartService.changeQuantity(request, Integer.parseInt(String.valueOf(this.request.getAttribute("user_id"))))) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Cart doesn't exist");
    }

    @GetMapping("/get_cart")
    public ResponseEntity<?> getMyCart() {
        return ResponseEntity.ok().body(cartService.getAllCart(Integer.parseInt(String.valueOf(request.getAttribute("user_id")))));
    }

    @DeleteMapping("/delete_cart")
    public ResponseEntity<?> deleteCart(@RequestParam("cart_id") long cartId) {
        if (cartService.deleteCart(cartId, Integer.parseInt(String.valueOf(this.request.getAttribute("user_id"))))) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Cart doesn't exist");
    }

    @PostMapping("/order")
    public ResponseEntity<?> orderCart(@RequestBody List<Long> userCartIds) {
        if(!orderService.queueOrder(userCartIds)){
            return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE,"Lỗi, không thể đặt món, hãy làm mới trang này");
        }
        return BaseResponse.success("ok");
    }
}
