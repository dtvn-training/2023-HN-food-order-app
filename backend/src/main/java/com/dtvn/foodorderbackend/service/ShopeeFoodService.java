package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.model.entity.DishCategory;
import com.dtvn.foodorderbackend.ulti.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

@Service
@RequiredArgsConstructor
public class ShopeeFoodService {
    Logger logger = LoggerFactory.getLogger(ShopeeFoodService.class);

    public RestaurantDetails getDishOfRestaurant(int deliveryId) throws Exception {
        HttpGet httpGet = new HttpGetWithHeaderFoody("https://gappapi.deliverynow.vn/api/dish/get_delivery_dishes?id_type=2&request_id=" + deliveryId);

        HttpResponse httpResponse = HttpClients.createDefault().execute(httpGet);

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        return new RestaurantDetails(GsonUtil.toJsonObject(builder.toString()));
    }

    public static class HttpGetWithHeaderFoody extends HttpGet {
        public HttpGetWithHeaderFoody(String uri) {
            super(uri);
            addHeader("x-foody-api-version", "1");
            addHeader("x-foody-app-type", "1004");
            addHeader("x-foody-client-id", "");
            addHeader("x-foody-client-language", "vi");
            addHeader("x-foody-client-type", "1");
            addHeader("x-foody-client-version", "3.0.0");
        }
    }

    public static class RestaurantDetails {
        Logger logger = LoggerFactory.getLogger(RestaurantDetails.class);
        Vector<DishCategoryDetails> menuInfos = new Vector<>();

        RestaurantDetails(JsonObject data) {

            if (data == null || data.get("result") == null || !data.get("result").getAsString().equals("success")) {
                logger.info("Failed to extract data from shopee food: {}", data);
                return;
            }
            data = data.get("reply").getAsJsonObject();
            if (data.get("menu_infos") == null) {
                logger.info("Failed to extract data from shopee food,while menu_infos not found: {}", data);
                return;
            }
            JsonArray array = data.get("menu_infos").getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonObject arrayElement = array.get(i).getAsJsonObject();
                DishCategoryDetails dishCategoryDetails = new DishCategoryDetails(arrayElement);
                menuInfos.add(dishCategoryDetails);
            }
            logger.info("Successfully extract data from shopee food");
        }
    }

    public static class DishCategoryDetails {
        Logger logger = LoggerFactory.getLogger(DishCategoryDetails.class);
        DishCategory dishCategory;
        Vector<Dish> listDishes = new Vector<>();

        DishCategoryDetails(JsonObject data) {
            if (data == null) {
                return;
            }
            dishCategory = new DishCategory();
            dishCategory.setId(data.get("dish_type_id").getAsLong());
            dishCategory.setName(data.get("dish_type_name").getAsString());

            JsonArray dishArray = data.get("dishes").getAsJsonArray();
            for (int i = 0; i < dishArray.size(); i++) {
                Dish dish = getDishFromJsonObject(dishArray.get(i).getAsJsonObject());
                listDishes.add(dish);
            }
        }

        Dish getDishFromJsonObject(JsonObject data) {
            Dish dish = new Dish();
            dish.setId(data.get("id").getAsLong());
            dish.setName(data.get("name").getAsString());
            if (data.get("discount_price") == null) {
                dish.setPrice(data.get("price").getAsJsonObject().get("value").getAsInt());
            } else {
                dish.setPrice(data.get("discount_price").getAsJsonObject().get("value").getAsInt());
            }
            dish.setDescription(data.get("description").getAsString());
            JsonArray photos = data.get("photos").getAsJsonArray();
            dish.setImage(photos.get(photos.size() - 1).getAsJsonObject().get("value").getAsString());
            logger.info("Successfully loaded dish, id: {}, name: {}",dish.getId(),dish.getName());
            return dish;
        }
    }
}
