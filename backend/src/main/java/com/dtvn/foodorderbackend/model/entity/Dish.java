package com.dtvn.foodorderbackend.model.entity;

import com.dtvn.foodorderbackend.ulti.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "dishes")
@NoArgsConstructor
public class Dish {
    @Id
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    Integer price;
    @Column(name = "description")
    String description;
    @Column(name = "like_count")
    Integer like;
    @Column(name = "image")
    String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    DishCategory category;

    public Dish(JsonObject data, DishCategory dishCategory) {
        this.category = dishCategory;
        id = data.get("id").getAsLong();
        name = data.get("name").getAsString();
        if (data.get("discount_price") == null) {
            price = data.get("price").getAsJsonObject().get("value").getAsInt();
        } else {
            price = data.get("discount_price").getAsJsonObject().get("value").getAsInt();
        }
        description = data.get("description").getAsString();
        String likeString = data.get("total_like").getAsString();
        if (!StringUtil.isNumber(likeString.charAt(likeString.length() - 1))) {
            likeString = likeString.substring(0, likeString.length() - 1);
        }
        like = Integer.parseInt(likeString);
        JsonArray photos = data.get("photos").getAsJsonArray();
        image = photos.get(photos.size() - 1).getAsJsonObject().get("value").getAsString();
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name;
    }
}
