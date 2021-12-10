package com.teamcode.creamy.Models;

import androidx.annotation.NonNull;

public class Flavor {
    private String name;
    private int imageId;
    private String imageUrl;

    public Flavor(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public Flavor(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return this.getName();
    }
}
