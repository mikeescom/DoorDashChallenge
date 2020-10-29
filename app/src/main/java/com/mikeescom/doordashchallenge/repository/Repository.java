package com.mikeescom.doordashchallenge.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.mikeescom.doordashchallenge.data.db.AppDatabase;
import com.mikeescom.doordashchallenge.data.models.Restaurant;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;
import com.mikeescom.doordashchallenge.data.network.Service;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private static final String BASE_URL = "https://api.doordash.com/";

    private AppDatabase db;
    private Service service;
    private MutableLiveData<Restaurant[]> restaurantsResponseMutableLiveData;
    private MutableLiveData<RestaurantDetail> restaurantDetailResponseMutableLiveData;

    public Repository(Context context) {
        restaurantsResponseMutableLiveData = new MutableLiveData<>();
        restaurantDetailResponseMutableLiveData = new MutableLiveData<>();

        db = Room.databaseBuilder(context,
                AppDatabase.class, "database-doordash").build();

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

    private void callRestaurants(double lat, double lng) {
        Log.i(TAG, "Restaurants - call to server");
        service.getRestaurants(lat, lng)
                .enqueue(new Callback<Restaurant[]>() {
                    @Override
                    public void onResponse(Call<Restaurant[]> call, Response<Restaurant[]> response) {
                        if (response.body() != null) {
                            insertRestaurants(response.body());
                            restaurantsResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Restaurant[]> call, Throwable t) {
                        restaurantsResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Restaurant[]> getRestaurantsResponseLiveData(double lat, double lng) {
        db.restaurantDao().getAll().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Restaurant[]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "Restaurants - onSubscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull Restaurant[] restaurants) {
                        Log.i(TAG, "Restaurants - onNext");
                        if (restaurants.length > 0) {
                            restaurantsResponseMutableLiveData.postValue(restaurants);
                        } else {
                            callRestaurants(lat, lng);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "Restaurants - onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "Restaurants - onComplete");
                    }
                });
        return restaurantsResponseMutableLiveData;
    }

    private void callRestaurantDetail(long id) {
        Log.i(TAG, "RestaurantDetail - call to server");
        service.getRestaurantDetail(id)
                .enqueue(new Callback<RestaurantDetail>() {
                    @Override
                    public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                        if (response.body() != null) {
                            insertRestaurantDetail(response.body());
                            restaurantDetailResponseMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                        restaurantDetailResponseMutableLiveData.postValue(null);
                    }
                });
    }

    public LiveData<RestaurantDetail> getRestaurantDetailResponseLiveData(long id) {
        db.restaurantDetailDao().getDetail(id).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<RestaurantDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "RestaurantDetail - onSubscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull RestaurantDetail restaurantDetail) {
                        Log.i(TAG, "RestaurantDetail - onNext");
                        restaurantDetailResponseMutableLiveData.postValue(restaurantDetail);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "RestaurantDetail - onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "RestaurantDetail - onComplete");
                        callRestaurantDetail(id);
                    }
                });
        return restaurantDetailResponseMutableLiveData;
    }

    public void insertRestaurants(Restaurant[] restaurants) {
        db.restaurantDao().deleteAll().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.i(TAG, "deleteAll - onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "deleteAll - onError: " + e.getMessage());
            }
        });
        db.restaurantDao().insertAll(restaurants).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.i(TAG, "insertAll - onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "insertAll - onError: " + e.getMessage());
            }
        });
    }

    public void insertRestaurantDetail(RestaurantDetail detail) {
        db.restaurantDetailDao().delete(detail).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.i(TAG, "delete - onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "delete - onError: " + e.getMessage());
            }
        });
        db.restaurantDetailDao().insert(detail).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.i(TAG, "insert - onComplete");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "insert - onError: " + e.getMessage());
            }
        });
    }
}
