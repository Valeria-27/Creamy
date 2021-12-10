package com.teamcode.creamy.Models;

import java.util.ArrayList;

public class User {
    private String id, name, email, password, address;
    private ArrayList<IceCream> Cart;

    public User() { }

    public User(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        Cart = new ArrayList<IceCream>();
    }

    public User(String id, String name, String email, String password, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        Cart = new ArrayList<IceCream>();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<IceCream> getCarrito() {
        return Cart;
    }

    public void setCarrito(ArrayList<IceCream> carrito) {
        Cart = carrito;
    }
}
