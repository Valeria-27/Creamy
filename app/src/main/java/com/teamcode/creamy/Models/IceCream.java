package com.teamcode.creamy.Models;

public class IceCream {
    private String IdIceCream;
    private String Flavor;
    private String Container;
    private double Price;

    public IceCream() {
    }

    public IceCream(String idIceCream, String flavor, String container, double price) {
        IdIceCream = idIceCream;
        Flavor = flavor;
        Container = container;
        Price = price;
    }

    public String getFlavor() {
        return Flavor;
    }

    public void setFlavor(String flavor) {
        Flavor = flavor;
    }

    public String getContainer() {
        return Container;
    }

    public void setContainer(String container) {
        Container = container;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public IceCream(String idIceCream) {
        IdIceCream = idIceCream;
    }

    public String getIdIceCream() {
        return IdIceCream;
    }

    public void setIdIceCream(String idIceCream) {
        IdIceCream = idIceCream;
    }
}
