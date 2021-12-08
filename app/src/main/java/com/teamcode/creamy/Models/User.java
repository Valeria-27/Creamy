package com.teamcode.creamy.Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String IdUser;
    private ArrayList<IceCream> Cart;

    public User() {
        Cart = new ArrayList<IceCream>();
    }

    public User(String idUser) {
        IdUser = idUser;
        Cart = new ArrayList<IceCream>();
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public ArrayList<IceCream> getCarrito() {
        return Cart;
    }

    public void setCarrito(ArrayList<IceCream> carrito) {
        Cart = carrito;
    }
}
