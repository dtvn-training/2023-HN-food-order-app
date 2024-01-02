package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "present_vote")
public class PresentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "url")
    String restaurantUrl;

    @Column(name = "description")
    String description;

    @Column(name = "user_id_created")
    long userIdCreated;

    @Column(name = "restaurant_name")
    String restaurantName;

    @Column(name = "total_vote")
    int totalVote;

    @Column(name = "active")
    boolean active;
}
