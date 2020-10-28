package com.mikeescom.doordashchallenge.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mikeescom.doordashchallenge.data.models.Restaurant;

import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    Maybe<Restaurant[]> getAll();

    @Query("SELECT Count(*) FROM restaurant")
    Maybe<Long> getCount();

    @Insert
    Completable insertAll(Restaurant... restaurants);

    @Query("DELETE FROM restaurant")
    Completable deleteAll();
}
