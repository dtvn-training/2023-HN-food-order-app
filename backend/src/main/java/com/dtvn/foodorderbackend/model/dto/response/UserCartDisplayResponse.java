package com.dtvn.foodorderbackend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserCartDisplayResponse {
    long id;
    long dishId;
    String dishName;
    String restaurantName;
    int unitPrice;
    int quantity;
}
