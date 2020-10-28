package com.mikeescom.doordashchallenge.data.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Restaurant {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @NonNull
    private long id;
    @ColumnInfo(name = "delivery_fee")
    @SerializedName("delivery_fee")
    private double delivery_fee;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "asap_time")
    @SerializedName("asap_time")
    private int asap_time;
    @ColumnInfo(name = "average_rating")
    @SerializedName("average_rating")
    private double average_rating;
    @ColumnInfo(name = "number_of_ratings")
    @SerializedName("number_of_ratings")
    private long number_of_ratings;
    @ColumnInfo(name = "cover_img_url")
    @SerializedName("cover_img_url")
    private String cover_img_url;
    @ColumnInfo(name = "header_img_url")
    @SerializedName("header_img_url")
    private String header_img_url;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAsap_time() {
        return asap_time;
    }

    public void setAsap_time(int asap_time) {
        this.asap_time = asap_time;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(double average_rating) {
        this.average_rating = average_rating;
    }

    public long getNumber_of_ratings() {
        return number_of_ratings;
    }

    public void setNumber_of_ratings(long number_of_ratings) {
        this.number_of_ratings = number_of_ratings;
    }

    public String getCover_img_url() {
        return cover_img_url;
    }

    public void setCover_img_url(String cover_img_url) {
        this.cover_img_url = cover_img_url;
    }

    public String getHeader_img_url() {
        return header_img_url;
    }

    public void setHeader_img_url(String header_img_url) {
        this.header_img_url = header_img_url;
    }
}
