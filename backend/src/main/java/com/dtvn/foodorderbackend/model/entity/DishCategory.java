package com.dtvn.foodorderbackend.model.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.Vector;

@Entity
@Data
@Table(name = "dish_category")
@NoArgsConstructor
public class DishCategory {
    @Id
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Dish> dishList;

    public DishCategory(JsonObject data,Restaurant restaurant) {
        if (data == null) {
            return;
        }
        setRestaurant(restaurant);
        id = data.get("dish_type_id").getAsLong();
        name = data.get("dish_type_name").getAsString();
        JsonArray dishArray = data.get("dishes").getAsJsonArray();
        dishList = new Vector<>();
        for (int i = 0; i < dishArray.size(); i++) {
            Dish dish = new Dish(dishArray.get(i).getAsJsonObject(),this);
            dishList.add(dish);
        }
    }
}
