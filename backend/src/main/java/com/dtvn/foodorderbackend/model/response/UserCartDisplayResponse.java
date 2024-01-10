package com.dtvn.foodorderbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserCartDisplayResponse {
    long id;
    String dishName;
    String restaurantName;
    int unitPrice;
    int quantity;
}
