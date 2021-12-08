package com.teamcode.creamy.Models;

import androidx.annotation.NonNull;

public class Container {
    private double price;
    private String type;
    private int imageId;
    private String imageUrl;

    public Container(double price, String type, int imageId) {
        this.price = price;
        this.type = type;
        this.imageId = imageId;
    }

    public Container(double price, String type, String imageUrl) {
        this.price = price;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getType();
    }
}
