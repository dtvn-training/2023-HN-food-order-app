package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.dto.request.AdminOrderRequest;
import com.dtvn.foodorderbackend.model.dto.response.AdminOrderResponse;
import com.dtvn.foodorderbackend.model.entity.Bill;
import com.dtvn.foodorderbackend.model.entity.ItemOrder;
import com.dtvn.foodorderbackend.model.entity.UserCart;
import com.dtvn.foodorderbackend.repository.BillRepository;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@RequiredArgsConstructor
@Service
public class OrderService {
    final HttpServletRequest httpServletRequest;
    final HttpServletResponse httpServletResponse;
    final UserCartRepository userCartRepository;
    final ItemOrderRepository itemOrderRepository;
    final UserRepository userRepository;
    final BillRepository billRepository;
    final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public boolean queueOrder(List<Long> userCartIds) {
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        List<UserCart> userCarts = userCartRepository.findAllById(userCartIds);
        // check all user carts contains user id

        for (UserCart cart : userCarts) {
            if (!cart.getCreatedById().equals(userId)) {
                return false;
            }
            // check exist in database
            if (userCarts.size() != userCartIds.size()) {
                return false;
            }
        }


        for (UserCart cart : userCarts) {
            ItemOrder order = new ItemOrder();
            order.setDishId(cart.getDishId());
            order.setQuantity(cart.getQuantity());
            order.setApproved(false);
            order.setDeleted(false);
            /*
                update base entity field
                dont need to check exists orders, user can have multiple same dish id order
             */
            order.update(userId);
            itemOrderRepository.save(order);
        }
        return true;
    }

    public AdminOrderResponse adminOrder(AdminOrderRequest adminOrderRequest) {
        long adminUserId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        // check exist
        List<ItemOrder> items = getAllItemOrderNotApproved();
        for (ItemOrder order : items) {
            if (!adminOrderRequest.getOrderIds().contains(order.getId())) {
                return null;
            }
        }
        // check success: all id presented

        // count the number of restaurant order item
        Map<Long, Integer> restaurantItemOrderCount = new HashMap<>();

        for (ItemOrder item : items) {
            long restaurantId = item.getDish().getCategory().getRestaurant().getDeliveryId();
            restaurantItemOrderCount.put(restaurantId, restaurantItemOrderCount.getOrDefault(restaurantId, 0) + 1);
        }
        logger.info("restaurant item count : {}", restaurantItemOrderCount);
        try {
            // calculate fee for each ItemOrder
            Map<Long, Integer> restaurantFeeByEachOrder = new HashMap<>();
            for (var restaurantCount : restaurantItemOrderCount.entrySet()) {
                // TODO: CAN DELETE IN USER CART IF NEEDED
                int index = adminOrderRequest.getRestaurantIds().indexOf(restaurantCount.getKey());
                int fee = adminOrderRequest.getRestaurantFees().get(index);
                int feeForEachOrder = fee / restaurantCount.getValue() + ((fee % restaurantCount.getValue()) == 0 ? 0 : 1);
                restaurantFeeByEachOrder.put(restaurantCount.getKey(), feeForEachOrder);

                // create bill
                List<Bill> bills = new Vector<>();
                for (ItemOrder item : items) {
                    // calculate price
                    int feeShip = restaurantFeeByEachOrder.get(item.getDish().getCategory().getRestaurant().getDeliveryId());
                    int price = item.getQuantity() * item.getDish().getPrice();
                    int discount = 0;// TODO: add discount
                    int finalPrice = price + feeShip - discount;

                    Bill bill = Bill.builder()
                            .orderId(item.getId())
                            .order(item)
                            .price(price)
                            .shipFee(feeShip)
                            .discount(discount)
                            .finalCost(finalPrice)
                            .build();
                    bill.update(adminUserId);
                    bills.add(bill);
                }
                // create bill success,
                // save bills
                billRepository.saveAll(bills);

                // set approved of ItemOrder is true
                for (ItemOrder item : items) {
                    item.setApproved(true);
                    item.update(adminUserId);
                }
                // balance value
                for (Bill bill : bills) {
                    userRepository.addBalanceById(bill.getOrder().getCreatedById(), -bill.getFinalCost());
                }// done
            }
        } catch (ArithmeticException e) {
            logger.error("Không thể chia cho 0");
            logger.error("{}", ExceptionUtils.getStackTrace(e));
            return null;
        } catch (Exception e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
            return null;
        }

        return new AdminOrderResponse();
    }


    public List<ItemOrder> getAllItemOrderNotApproved() {
        List<ItemOrder> itemOrderList = itemOrderRepository.findByApprovedAndDeleted(false, false);
        return itemOrderList;
    }

    public List<ItemOrder> getAll() {
        List<ItemOrder> itemOrderList = itemOrderRepository.findByApprovedAndDeleted(false, false);
        return itemOrderList;
    }

    public boolean deleteOrderItem(long orderId) {
        long adminUserId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
        ItemOrder itemOrder = itemOrderRepository.findByIdAndApprovedFalseAndDeletedFalse(orderId);
        if (itemOrder == null) {
            // cannot find the satisfy order
            return false;
        }
        itemOrder.update(adminUserId);
        itemOrder.setDeleted(true);
        itemOrderRepository.save(itemOrder);
        return true;
    }
}
