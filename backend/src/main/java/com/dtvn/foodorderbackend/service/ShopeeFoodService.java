package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.Dish;
import com.dtvn.foodorderbackend.model.entity.DishCategory;
import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.repository.DishRepository;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import com.dtvn.foodorderbackend.ulti.GsonUtil;
import com.dtvn.foodorderbackend.ulti.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

@Service
@RequiredArgsConstructor
public class ShopeeFoodService {
    final RestaurantRepository restaurantRepository;
    final DishRepository dishRepository;


    public Restaurant getRestaurantDishes(int deliveryId) throws Exception {
        HttpGet httpGetDish = new HttpGetWithHeaderFoody("https://gappapi.deliverynow.vn/api/dish/get_delivery_dishes?id_type=2&request_id=" + deliveryId);
        HttpResponse httpResponseDish = HttpClients.createDefault().execute(httpGetDish);
        InputStream inputStreamDish = httpResponseDish.getEntity().getContent();
        String dataDish = StringUtil.getFromInputStream(inputStreamDish);
        HttpGet httpGetDetail = new HttpGetWithHeaderFoody("https://gappapi.deliverynow.vn/api/delivery/get_detail?id_type=2&request_id=" + deliveryId);

        HttpResponse httpResponseDetail = HttpClients.createDefault().execute(httpGetDetail);
        InputStream inputStreamDetail = httpResponseDetail.getEntity().getContent();
        String dataDetail = StringUtil.getFromInputStream(inputStreamDetail);
        System.out.println(dataDetail);
        return new Restaurant(GsonUtil.toJsonObject(dataDetail), GsonUtil.toJsonObject(dataDish));
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
}
