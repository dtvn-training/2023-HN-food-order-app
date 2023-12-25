package com.dtvn.foodorderbackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "dish_category")
public class DishCategory {
    @Id
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;
}
