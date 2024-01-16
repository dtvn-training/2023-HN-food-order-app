package com.dtvn.foodorderbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Vector;

@Entity
@Data
@Table(name = "dish_category")
@NoArgsConstructor
public class DishCategory implements RecordEntity {
    @Id
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    Restaurant restaurant;

    @Column(name = "active")
    boolean active = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Dish> dishList;

    public DishCategory(JsonObject data, Restaurant restaurant) {
        if (data == null) {
            return;
        }
        this.restaurant = restaurant;
        id = data.get("dish_type_id").getAsLong();
        name = data.get("dish_type_name").getAsString();
        JsonArray dishArray = data.get("dishes").getAsJsonArray();
        dishList = new Vector<>();
        for (int i = 0; i < dishArray.size(); i++) {
            Dish dish = new Dish(dishArray.get(i).getAsJsonObject(), this);
            dishList.add(dish);
        }
    }

    @Override
    public void enActive() {
        active = true;
    }

    @Override
    public void deActive() {
        active = false;
        for (Dish dish : dishList) {
            dish.deActive();
        }
    }

    /**
     * not use
     */
    @Override
    public void delete() {

    }
}
