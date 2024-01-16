package com.dtvn.foodorderbackend.controller;

import com.dtvn.foodorderbackend.model.dto.request.cart.CartRequest;
import com.dtvn.foodorderbackend.model.dto.request.cart.ChangeQuantityCartRequest;
import com.dtvn.foodorderbackend.model.dto.response.BaseResponse;
import com.dtvn.foodorderbackend.service.CartService;
import com.dtvn.foodorderbackend.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    final CartService cartService;
    final HttpServletRequest request;
    final OrderService orderService;
    @SuppressWarnings("unused")
    Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody @Valid CartRequest request) {
        if (cartService.addToCart(request, Integer.parseInt(String.valueOf(this.request.getAttribute("user_id"))))) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Cart exist or not valid dish id");
    }

    @PutMapping("/change-quantity")
    public ResponseEntity<?> changeQuantity(@RequestBody @NonNull @Valid ChangeQuantityCartRequest request) {
        if (cartService.changeQuantity(request, Integer.parseInt(String.valueOf(this.request.getAttribute("user_id"))))) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Cart doesn't exist");
    }

    @GetMapping("/get-cart")
    public ResponseEntity<?> getMyCart() {
        return ResponseEntity.ok().body(cartService.getAllCart(Integer.parseInt(String.valueOf(request.getAttribute("user_id")))));
    }

    @DeleteMapping("/delete-cart")
    public ResponseEntity<?> deleteCart(@RequestParam("cart_id") long cartId) {
        if (cartService.deleteCart(cartId, Integer.parseInt(String.valueOf(this.request.getAttribute("user_id"))))) {
            return BaseResponse.success();
        }
        return BaseResponse.createError(HttpStatus.NOT_ACCEPTABLE, "Không tồn tại trong giỏ hàng");
    }
}
