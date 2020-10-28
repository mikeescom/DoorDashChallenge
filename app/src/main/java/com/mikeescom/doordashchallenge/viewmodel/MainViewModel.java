package com.mikeescom.doordashchallenge.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.mikeescom.doordashchallenge.network.models.Restaurant;
import com.mikeescom.doordashchallenge.network.models.RestaurantDetail;
import com.mikeescom.doordashchallenge.repository.Repository;

public class MainViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Restaurant[]> restaurantsResponseLiveData;
    private LiveData<RestaurantDetail> restaurantDetailResponseLiveData;


    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repository = new Repository();
        restaurantsResponseLiveData = repository.getRestaurantsResponseLiveData();
        restaurantDetailResponseLiveData = repository.getRestaurantDetailResponseLiveData();
    }

    public void callRestaurants(double lat, double lng) {
        repository.callRestaurants(lat, lng);
    }

    public LiveData<Restaurant[]> getRestaurantsResponseLiveData() {
        return restaurantsResponseLiveData;
    }

    public void callRestaurantDetail(int id) {
        repository.callRestaurantDetail(id);
    }

    public LiveData<RestaurantDetail> getRestaurantDetailResponseLiveData() {
        return restaurantDetailResponseLiveData;
    }

}
