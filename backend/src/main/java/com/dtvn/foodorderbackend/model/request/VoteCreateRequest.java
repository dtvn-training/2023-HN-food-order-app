package com.dtvn.foodorderbackend.model.request;

import lombok.Data;

@Data
public class VoteCreateRequest {
    String restaurantUrl;

    String description;

    String restaurantName;
}
