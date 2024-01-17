package com.dtvn.foodorderbackend.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PresentVoteUserResponse {
    long id;
    String restaurantUrl;
    String description;
    String restaurantName;
    int totalVote;
    long userIdCreated;
    String createdName;
    Boolean isVoted;
}
