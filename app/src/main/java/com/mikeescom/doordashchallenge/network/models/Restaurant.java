package com.mikeescom.doordashchallenge.network.models;

import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("id")
    private
    long id;
    @SerializedName("delivery_fee")
    private
    double delivery_fee;
    @SerializedName("name")
    private
    String name;
    @SerializedName("asap_time")
    private
    int asap_time;
    @SerializedName("average_rating")
    private
    double average_rating;
    @SerializedName("number_of_ratings")
    private
    long number_of_ratings;
    @SerializedName("cover_img_url")
    private
    String cover_img_url;
    @SerializedName("header_img_url")
    private
    String header_img_url;

    public long getId() {
        return id;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public String getName() {
        return name;
    }

    public int getAsap_time() {
        return asap_time;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public long getNumber_of_ratings() {
        return number_of_ratings;
    }

    public String getCover_img_url() {
        return cover_img_url;
    }

    public String getHeader_img_url() {
        return header_img_url;
    }
}
