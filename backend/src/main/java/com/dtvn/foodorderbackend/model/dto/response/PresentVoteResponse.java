package com.dtvn.foodorderbackend.model.dto.response;

import com.dtvn.foodorderbackend.model.entity.PresentVote;
import com.dtvn.foodorderbackend.repository.PresentVoteRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PresentVoteResponse {
    long id;
    String restaurantUrl;
    String description;
    String restaurantName;
    int totalVote;
    boolean active;
    long userIdCreated;
    String createdName;

    public PresentVoteResponse(PresentVote vote){
        this.id = vote.getId();
        this.restaurantUrl = vote.getRestaurantUrl();
        this.description = vote.getDescription();
        this.restaurantName = vote.getRestaurantName();
        this.totalVote = vote.getTotalVote();
        this.active = vote.isActive();
        this.userIdCreated = vote.getCreatedBy().getId();
        this.createdName = vote.getCreatedBy().getFullName();
    }
}
