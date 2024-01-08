package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.entity.UserCart;
import com.dtvn.foodorderbackend.model.request.CartRequest;
import com.dtvn.foodorderbackend.model.request.ItemOrderRequest;
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
        if (userCartRepository.existsByUserIdAndDishId(userId, request.getDishId())) {
            return false;
        }
        try {
            userCartRepository.save(
                    UserCart.builder()
                            .userId(userId)
                            .dishId(request.getDishId())
                            .quantity(request.getQuantity())
                            .build()
            );
            return true;
        } catch (Exception e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    public boolean changeQuantity(CartRequest request, long userId) {
        if (!userCartRepository.existsByUserIdAndDishId(userId, request.getDishId())) {
            return false;
        }
        userCartRepository.changeQuantityByUserIdAndDishId(userId, request.getDishId(), request.getQuantity());
        return true;
    }

    public List<UserCart> getAllCart(long userId) {
        User user = userRepository.findUserById(userId).orElseThrow();
        return user.getCarts();
    }

    public boolean deleteCart(CartRequest request, long userId) {
        if (!userCartRepository.existsByUserIdAndDishId(userId, request.getDishId())) {
            return false;
        }
        userCartRepository.deleteByUserIdAndDishId(userId, request.getDishId());
        return true;
    }

    public void queueOrder(List<ItemOrderRequest> itemOrders) {
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        for (ItemOrderRequest item : itemOrders) {
            ItemOrder order = new ItemOrder();
            order.setDishId(item.getDishId());
            order.setQuantity(item.getQuantity());
            order.setApproved(false);
            order.update(userId);
            itemOrderRepository.save(order);
        }
    }
}
