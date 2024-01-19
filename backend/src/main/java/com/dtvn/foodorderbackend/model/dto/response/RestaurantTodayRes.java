package com.dtvn.foodorderbackend.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestaurantTodayRes {
    private long id;
    private String name;
    private String address;
    private double rating;
    private String img;
}
