package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;


@Entity
@Table(name = "item_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemOrder extends BaseEntity {
    //same to cart id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "dish_id")
    long dishId;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "approved")
    boolean approved = false;
}
