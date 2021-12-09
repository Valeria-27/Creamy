package com.teamcode.creamy.Models;

public class IceCream {
    private String idIceCream;
    private String imageUrl;
    private String flavor;
    private String container;
    private double price;

    public IceCream() {
    }

    public IceCream(String flavor, String container, double price) {
        this.flavor = flavor;
        this.container = container;
        this.price = price;
    }

    public IceCream(String imageUrl, String container, String flavor, double price) {
        this.imageUrl = imageUrl;
        this.flavor = flavor;
        this.container = container;
        this.price = price;
    }

    public IceCream(String idIceCream) {
        this.idIceCream = idIceCream;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIdIceCream() {
        return idIceCream;
    }

    public void setIdIceCream(String idIceCream) {
        this.idIceCream = idIceCream;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

