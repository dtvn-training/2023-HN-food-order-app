package com.dtvn.foodorderbackend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
@AllArgsConstructor
@Builder
@Data
public class ItemOrderDisplayResponse {

    // table item_order
    long id;
    Timestamp createdTime;
    int quantity;
    // --> map = userId

    // table user
    String userFullName;

    // dishes
    String dishName;
    int unitPrice;

    // restaurant
    String restaurantName;
    long restaurantId;
    String restaurantAddress;
}
