package com.dtvn.foodorderbackend.model.request;

import lombok.Data;

@Data
public class ItemOrderRequest {
    long dishId;
    int quantity;
}
