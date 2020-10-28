package com.mikeescom.doordashchallenge.data.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;

import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static RestaurantDetail.Address fromString(String value) {
        Type listType = new TypeToken<RestaurantDetail.Address>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromAddress(RestaurantDetail.Address address) {
        Gson gson = new Gson();
        String json = gson.toJson(address);
        return json;
    }
}
