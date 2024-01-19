package com.dtvn.foodorderbackend.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DishRes {
    private Long id;
    private String name;
    private int price;
    private String image;
    private String restaurantName;
}
