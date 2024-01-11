package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.dto.request.cart.CartRequest;
import com.dtvn.foodorderbackend.model.dto.request.cart.ChangeQuantityCartRequest;
import com.dtvn.foodorderbackend.model.dto.response.UserCartDisplayResponse;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.entity.UserCart;
import com.dtvn.foodorderbackend.repository.DishRepository;
import com.dtvn.foodorderbackend.repository.ItemOrderRepository;
import com.dtvn.foodorderbackend.repository.UserCartRepository;
import com.dtvn.foodorderbackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    final UserCartRepository userCartRepository;
    final HttpServletResponse response;
    final DishRepository dishRepository;
    final UserRepository userRepository;
    @SuppressWarnings("unused")
    Logger logger = LoggerFactory.getLogger(CartService.class);
    final ItemOrderRepository itemOrderRepository;
    final HttpServletRequest httpServletRequest;

    /*
        If exist the dish in user cart, return false
     */
    public boolean addToCart(CartRequest request, long userId) {
        if (!dishRepository.existsById(request.getDishId())) {
            return false;
        }
        if (userCartRepository.existsByCreatedByIdAndDishId(userId, request.getDishId())) {
            return false;
        }
        try {
            UserCart userCart = UserCart.builder()
                    .dishId(request.getDishId())
                    .quantity(request.getQuantity())
                    .build();
            userCart.update(userId);

            userCartRepository.save(
                    userCart
            );
            return true;
        } catch (Exception e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean changeQuantity(ChangeQuantityCartRequest request, long userId) {
        UserCart userCart = userCartRepository.findByCreatedByIdAndId(userId, request.getUserCartId());
        if (userCart == null) return false;
        userCart.setQuantity(request.getQuantity());
        // DONE: update base entity
        userCart.update(userId);
        userCartRepository.save(userCart);
        return true;
    }

    public List<UserCartDisplayResponse> getAllCart(long userId) {
        User user = userRepository.findUserById(userId).orElseThrow();
        return user.getCarts().stream().map(UserCart::toDisplayResponse).toList();
    }

    public boolean deleteCart(long userCartId, long userId) {
        if (!userCartRepository.existsByCreatedByIdAndId(userId, userCartId)) {
            return false;
        }
        userCartRepository.deleteByCreatedByIdAndId(userId, userCartId);
        return true;
    }

}
