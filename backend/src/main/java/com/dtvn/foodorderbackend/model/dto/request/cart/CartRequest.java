package com.dtvn.foodorderbackend.model.dto.request.cart;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CartRequest {
    long dishId;
    @Range(min = 1, max = (int) 1e7)
    int quantity;
}
