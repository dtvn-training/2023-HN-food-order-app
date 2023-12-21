package com.dtvn.foodorderbackend.ulti;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonUtil {
    private static Gson gson = new Gson();
    public static JsonObject toJsonObject(String o){
        return gson.fromJson(o, JsonObject.class);
    }
}
