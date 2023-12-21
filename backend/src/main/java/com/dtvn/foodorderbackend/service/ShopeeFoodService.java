package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.ulti.GsonUtil;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class ShopeeFoodService {
    public JsonObject getDishOfRestaurant(int restaurantId) throws Exception {
        HttpGet httpGet = new HttpGet("https://gappapi.deliverynow.vn/api/dish/get_delivery_dishes?id_type=2&request_id=" + restaurantId);
        httpGet.addHeader("x-foody-api-version", "1");
        httpGet.addHeader("x-foody-app-type", "1004");
        httpGet.addHeader("x-foody-client-id", "");
        httpGet.addHeader("x-foody-client-language", "vi");
        httpGet.addHeader("x-foody-client-type", "1");
        httpGet.addHeader("x-foody-client-version", "3.0.0");

        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(httpGet);

        InputStream inputStream = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        return GsonUtil.toJsonObject(builder.toString());
    }
}
