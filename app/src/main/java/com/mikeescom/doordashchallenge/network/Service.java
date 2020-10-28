package com.mikeescom.doordashchallenge.network;

import com.mikeescom.doordashchallenge.network.models.Restaurant;
import com.mikeescom.doordashchallenge.network.models.RestaurantDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("/v2/restaurant/")
    Call<Restaurant[]> getRestaurants(@Query("lat") double lat, @Query("lng") double lng);

    @GET("/v2/restaurant/{id}/")
    Call<RestaurantDetail> getRestaurantDetail(@Path("id") int id);
}
