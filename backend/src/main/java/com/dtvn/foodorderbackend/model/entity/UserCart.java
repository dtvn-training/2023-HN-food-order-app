package com.dtvn.foodorderbackend.model.entity;

import com.dtvn.foodorderbackend.model.response.UserCartDisplayResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "user_cart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCart extends BaseEntity {
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

    public UserCartDisplayResponse toDisplayResponse() {
        return UserCartDisplayResponse.builder()
                .id(id)
                .dishName(getDish().getName())
                .restaurantName(getDish().getCategory().getRestaurant().getName())
                .unitPrice(getDish().getPrice())
                .quantity(getQuantity())
                .build();
    }
}
