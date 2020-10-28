package com.mikeescom.doordashchallenge.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mikeescom.doordashchallenge.network.models.Restaurant;
import com.mikeescom.doordashchallenge.network.models.RestaurantDetail;
import com.mikeescom.doordashchallenge.network.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String BASE_URL = "https://api.doordash.com/";

    private Service service;
    private MutableLiveData<Restaurant[]> restaurantsResponseMutableLiveData;
    private MutableLiveData<RestaurantDetail> restaurantDetailResponseMutableLiveData;

    public Repository() {
        restaurantsResponseMutableLiveData = new MutableLiveData<>();
        restaurantDetailResponseMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        service = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service.class);
    }

    public void callRestaurants(double lat, double lng) {
        service.getRestaurants(lat, lng)
                .enqueue(new Callback<Restaurant[]>() {
                    @Override
                    public void onResponse(Call<Restaurant[]> call, Response<Restaurant[]> response) {
                        if (response.body() != null) {
                            restaurantsResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Restaurant[]> call, Throwable t) {
                        restaurantsResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Restaurant[]> getRestaurantsResponseLiveData() {
        return restaurantsResponseMutableLiveData;
    }

    public void callRestaurantDetail(int id) {
        service.getRestaurantDetail(id)
                .enqueue(new Callback<RestaurantDetail>() {
                    @Override
                    public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                        if (response.body() != null) {
                            restaurantDetailResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                        restaurantDetailResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<RestaurantDetail> getRestaurantDetailResponseLiveData() {
        return restaurantDetailResponseMutableLiveData;
    }
}
