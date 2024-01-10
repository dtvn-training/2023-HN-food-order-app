package com.dtvn.foodorderbackend.model.dto.request;

import lombok.Data;

@Data
public class VoteCreateRequest {
    String restaurantUrl;

    String description;

    String restaurantName;
}
