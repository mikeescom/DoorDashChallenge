package com.mikeescom.doordashchallenge.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mikeescom.doordashchallenge.data.models.Restaurant;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;
import com.mikeescom.doordashchallenge.repository.Repository;

public class MainViewModel extends AndroidViewModel {
    private Context context;
    private Repository repository;
    private LiveData<Restaurant[]> restaurantsResponseLiveData;
    private LiveData<RestaurantDetail> restaurantDetailResponseLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void init() {
        repository = new Repository(context);
    }

    public LiveData<Restaurant[]> getRestaurantsResponseLiveData(double lat, double lng) {
        restaurantsResponseLiveData = repository.getRestaurantsResponseLiveData(lat, lng);
        return restaurantsResponseLiveData;
    }

    public LiveData<RestaurantDetail> getRestaurantDetailResponseLiveData(int id) {
        restaurantDetailResponseLiveData = repository.getRestaurantDetailResponseLiveData(id);
        return restaurantDetailResponseLiveData;
    }

}
