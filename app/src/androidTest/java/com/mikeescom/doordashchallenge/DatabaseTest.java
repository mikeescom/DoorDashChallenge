package com.mikeescom.doordashchallenge;



import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mikeescom.doordashchallenge.data.db.AppDatabase;
import com.mikeescom.doordashchallenge.data.models.Restaurant;
import com.mikeescom.doordashchallenge.data.models.RestaurantDetail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private AppDatabase mockDatabase;

    @Before
    public void initDB() {
        mockDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class).build();
    }

    @After
    public void closeDB() {
        mockDatabase.close();
    }

    @Test
    public void insertAll_Restaurants() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1);
        Restaurant restaurant2 = new Restaurant();
        restaurant1.setId(2);

        Restaurant[] restaurants
                = new Restaurant[]{restaurant1, restaurant2};

        mockDatabase.restaurantDao().insertAll(restaurants).blockingAwait();
        mockDatabase.restaurantDao().getAll().test().assertValue(list -> list.length == 2);
    }

    @Test
    public void deleteAll_Restaurants() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1);
        Restaurant restaurant2 = new Restaurant();
        restaurant1.setId(2);

        Restaurant[] restaurants
                = new Restaurant[]{restaurant1, restaurant2};

        mockDatabase.restaurantDao().insertAll(restaurants).blockingAwait();
        mockDatabase.restaurantDao().getAll().test().assertValue(list -> list.length == 2);
        mockDatabase.restaurantDao().deleteAll().blockingAwait();
        mockDatabase.restaurantDao().getAll().test().assertValue(list -> list.length == 0);
    }

    @Test
    public void insert_RestaurantDetail() {
        RestaurantDetail detail = new RestaurantDetail();
        detail.setId("1");

        mockDatabase.restaurantDetailDao().insert(detail).blockingAwait();
        mockDatabase.restaurantDetailDao().getDetail(1).test().assertValue(list -> list.getId().equals("1"));
    }

    @Test
    public void delete_RestaurantDetail() {
        RestaurantDetail detail = new RestaurantDetail();
        detail.setId("1");

        mockDatabase.restaurantDetailDao().insert(detail).blockingAwait();
        mockDatabase.restaurantDetailDao().getDetail(1).test().assertValue(list -> list.getId().equals("1"));
        mockDatabase.restaurantDetailDao().delete(detail).blockingAwait();
        mockDatabase.restaurantDetailDao().getDetail(1).test().assertNoValues();
    }
}
