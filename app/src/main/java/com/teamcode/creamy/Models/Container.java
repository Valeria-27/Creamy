package com.teamcode.creamy.Models;

public class Container {
    private double price;
    private ContainerType type;
    private ContainerSize size;
    private int imageId;

    public Container(double price, ContainerType type, ContainerSize size, int imageId) {
        this.price = price;
        this.type = type;
        this.size = size;
        this.imageId = imageId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }

    public ContainerSize getSize() {
        return size;
    }

    public void setSize(ContainerSize size) {
        this.size = size;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
