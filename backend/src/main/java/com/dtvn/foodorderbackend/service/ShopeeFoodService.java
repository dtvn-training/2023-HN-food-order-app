package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.Restaurant;
import com.dtvn.foodorderbackend.repository.DishRepository;
import com.dtvn.foodorderbackend.repository.RestaurantRepository;
import com.dtvn.foodorderbackend.ulti.GsonUtil;
import com.dtvn.foodorderbackend.ulti.StringUtil;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ShopeeFoodService {
    final RestaurantRepository restaurantRepository;
    final DishRepository dishRepository;



    public Restaurant getRestaurantDishes(long deliveryId) throws Exception {
        HttpGetWithHeaderFoody httpGetDish = new HttpGetWithHeaderFoody("https://gappapi.deliverynow.vn/api/dish/get_delivery_dishes?id_type=2&request_id=" + deliveryId);
        String dataDish = httpGetDish.execute();

        HttpGetWithHeaderFoody httpGetDetail = new HttpGetWithHeaderFoody("https://gappapi.deliverynow.vn/api/delivery/get_detail?id_type=2&request_id=" + deliveryId);
        String dataDetail = httpGetDetail.execute();

        return new Restaurant(GsonUtil.toJsonObject(dataDetail), GsonUtil.toJsonObject(dataDish));
    }

    public boolean fetchRestaurantData(String url) throws Exception {
        String subPath = url.substring("https://shopeefood.vn/".length());
        HttpGetWithHeaderFoody getRestaurantId = new HttpGetWithHeaderFoody("https://gappapi.deliverynow.vn/api/delivery/get_from_url?url=" + subPath);

        JsonObject data = GsonUtil.toJsonObject(getRestaurantId.execute());
        if (data.get("reply") == null) return false;
        int deliveryId = data.get("reply").getAsJsonObject().get("delivery_id").getAsInt();
        if (deliveryId == 0) return false;
        fetchRestaurantDataToDatabaseById(deliveryId);
        return true;
    }

    private void fetchRestaurantDataToDatabaseById(int deliveryId) throws Exception {
        // TODO: remove the data exist in database but not present in data fetched by shopee (foody)
        Restaurant restaurant = getRestaurantDishes(deliveryId);
        restaurantRepository.save(restaurant);
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

        public String execute() throws Exception {
            HttpResponse httpResponse = HttpClients.createDefault().execute(this);
            InputStream inputStream = httpResponse.getEntity().getContent();
            return StringUtil.getFromInputStream(inputStream);
        }
    }
}
