package com.mikeescom.doordashchallenge.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface RestaurantDetailDao {
    @Query("SELECT * FROM restaurantdetail WHERE id = :id")
    Flowable<RestaurantDetail> getDetail(int id);

    @Query("SELECT Count(*) FROM restaurantdetail WHERE id = :id")
    Maybe<Long> getCount(int id);

    @Insert
    Completable insert(RestaurantDetail detail);

    @Delete
    Completable delete(RestaurantDetail detail);
}
