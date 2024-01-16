package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "present_vote")
public class PresentVote extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "url")
    String restaurantUrl;

    @Column(name = "description")
    String description;

    @Column(name = "restaurant_name")
    String restaurantName;

    @Column(name = "total_vote")
    int totalVote;

    @Column(name = "active")
    boolean active;
}
