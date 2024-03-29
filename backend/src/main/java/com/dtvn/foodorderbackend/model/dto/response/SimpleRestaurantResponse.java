package com.dtvn.foodorderbackend.model.dto.response;

import lombok.Data;

@Data
public class SimpleRestaurantResponse {
    long deliveryId;
    String name;
    String description;
    String address;
    double rating;
    String uri;
    String image;
    boolean selected;
}
