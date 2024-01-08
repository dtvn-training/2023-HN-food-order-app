package com.dtvn.foodorderbackend.model.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CartRequest {

    long dishId;
    @Size(min = 1, max = 5000000)
    @Nullable
    Integer quantity;
}
