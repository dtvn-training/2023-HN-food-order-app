package com.dtvn.foodorderbackend.model.dto.response;

import lombok.Data;

@Data
public class SimpleDishResponse {
    long id; // dish id
    String dishName;

    String restaurantName;
    int price;

    boolean deleted;
}
