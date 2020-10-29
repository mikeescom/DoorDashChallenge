package com.mikeescom.doordashchallenge.data.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class RestaurantDetail {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @NonNull
    private String id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "cover_img_url")
    @SerializedName("cover_img_url")
    private String cover_img_url;
    @ColumnInfo(name = "phone_number")
    @SerializedName("phone_number")
    private double phone_number;
    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;
    @ColumnInfo(name = "header_img_url")
    @SerializedName("header_img_url")
    private String header_img_url;
    @ColumnInfo(name = "address")
    @SerializedName("address")
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_img_url() {
        return cover_img_url;
    }

    public void setCover_img_url(String cover_img_url) {
        this.cover_img_url = cover_img_url;
    }

    public double getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(double phone_number) {
        this.phone_number = phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeader_img_url() {
        return header_img_url;
    }

    public void setHeader_img_url(String header_img_url) {
        this.header_img_url = header_img_url;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address {
        @ColumnInfo(name = "printable_address")
        @SerializedName("printable_address")
        private String printable_address;
        @ColumnInfo(name = "lat")
        @SerializedName("lat")
        private double lat;
        @ColumnInfo(name = "lng")
        @SerializedName("lng")
        private double lng;

        public String getPrintable_address() {
            return printable_address;
        }

        public void setPrintable_address(String printable_address) {
            this.printable_address = printable_address;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
