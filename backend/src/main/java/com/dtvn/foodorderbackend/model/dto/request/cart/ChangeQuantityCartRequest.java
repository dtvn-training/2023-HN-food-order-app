package com.dtvn.foodorderbackend.model.dto.request.cart;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ChangeQuantityCartRequest {
    long userCartId;
    @Range(min = 1, max = (int) 1e7)
    int quantity;
}
