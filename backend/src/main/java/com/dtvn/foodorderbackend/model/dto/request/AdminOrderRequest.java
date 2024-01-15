package com.dtvn.foodorderbackend.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AdminOrderRequest {
    List<Long> orderIds;

    List<Long> restaurantIds;
    List<Integer> restaurantFees;
}
