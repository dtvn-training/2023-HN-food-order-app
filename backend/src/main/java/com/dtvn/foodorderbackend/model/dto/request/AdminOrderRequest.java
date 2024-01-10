package com.dtvn.foodorderbackend.model.dto.request;

import jakarta.persistence.Tuple;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;

@Data
public class AdminOrderRequest {
    List<Long> orderIds;

    List<Long> restaurantIds;
    List<Integer> restaurantFees;
}
