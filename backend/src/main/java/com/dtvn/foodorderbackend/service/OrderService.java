package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import com.dtvn.foodorderbackend.model.entity.UserCart;
import com.dtvn.foodorderbackend.repository.ItemOrderRepository;
import com.dtvn.foodorderbackend.repository.UserCartRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    final HttpServletRequest httpServletRequest;
    final UserCartRepository userCartRepository;
    final ItemOrderRepository itemOrderRepository;

    public boolean queueOrder(List<Long> userCartIds) {
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        List<UserCart> userCarts = userCartRepository.findAllById(userCartIds);


        for (UserCart cart : userCarts) {
            ItemOrder order = new ItemOrder();
            order.setDishId(cart.getDishId());
            order.setQuantity(cart.getQuantity());
            order.setApproved(false);
            /*
                update base entity field
             */
            order.update(userId);
            itemOrderRepository.save(order);
        }
    }

    public List<ItemOrder> getAllItemOrderNotApproved() {
        return itemOrderRepository.findAllByApprovedFalseAndDeletedFalse();
    }
}
