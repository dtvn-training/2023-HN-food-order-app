package com.dtvn.foodorderbackend.model.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Vector;

@Entity
@Data
@Table(name = "restaurant")
@NoArgsConstructor
/*
  id: delivery id for restaurant
 */
public class Restaurant {
    @Id
    @Column(name = "id")
    long deliveryId;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "address")
    String address;

    @Column(name = "rating")
    double rating;

    @Column(name = "url")
    String url;

    @Column(name = "image")
    String image;

    @Column(name = "selected")
    boolean selected = false;

    @Column(name = "deleted")
    boolean deleted = false;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DishCategory> dishCategoryList;

    public Restaurant(JsonObject data, JsonObject dishDeliveryData) {
        if (data.get("result") == null || !data.get("result").getAsString().equals("success")) {
            return;
        }
        data = data.get("reply").getAsJsonObject().get("delivery_detail").getAsJsonObject();
        this.deliveryId = data.get("delivery_id").getAsLong();
        this.name = data.get("name").getAsString();
        this.description = ""; // Todo: cannot find description in sample data
        this.address = data.get("address").getAsString();
        this.rating = data.get("rating").getAsJsonObject().get("avg").getAsDouble();
        this.url = data.get("url").getAsString();
        JsonArray imageArr = data.get("photos").getAsJsonArray();
        this.image = imageArr.get(imageArr.size() - 1).getAsJsonObject().get("value").getAsString();

        dishCategoryList = new Vector<>();
        JsonArray dishCategoryArray = dishDeliveryData.get("reply").getAsJsonObject().get("menu_infos").getAsJsonArray();
        for (int i = 0; i < dishCategoryArray.size(); i++) {
            JsonObject categoryJsonData = dishCategoryArray.get(i).getAsJsonObject();
            if (categoryJsonData.get("dish_type_id") != null && categoryJsonData.get("dish_type_id").getAsLong() > 0) {
                dishCategoryList.add(new DishCategory(categoryJsonData, this));
            }
        }
    }
}
