package com.mikeescom.doordashchallenge.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mikeescom.doordashchallenge.data.models.Restaurant;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;

@Database(entities = {Restaurant.class, RestaurantDetail.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
    public abstract RestaurantDetailDao restaurantDetailDao();
}
