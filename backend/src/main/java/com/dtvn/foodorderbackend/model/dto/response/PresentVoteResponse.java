package com.dtvn.foodorderbackend.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresentVoteResponse {
    long id;
    String restaurantUrl;
    String description;
    String restaurantName;
    int totalVote;
    long userIdCreated;
    String createdName;
}
