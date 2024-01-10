package com.dtvn.foodorderbackend.model.dto.request;

import lombok.Data;

@Data
public class ItemOrderRequest {
    long dishId;
    int quantity;
}
