package com.dtvn.foodorderbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_cart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne(targetEntity = Dish.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", insertable = false, updatable = false)
    @JsonIgnore
    Dish dish;

    @Column(name = "dish_id")
    long dishId;

    @Column(name = "quantity")
    int quantity;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    User user;

    @Column(name = "user_id")
    @JsonIgnore
    long userId;
}
