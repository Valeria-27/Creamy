package com.teamcode.creamy.Models;

public class IceCream {
    private  int imgFoto;
    private String recipiente;
    private String sabores;
    private double precio;


    public IceCream(int imgFoto, String recipiente, String sabores, double precio) {
        this.imgFoto = imgFoto;
        this.recipiente = recipiente;
        this.sabores = sabores;
        this.precio = precio;
    }

    public IceCream() {
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public String getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(String recipiente) {
        this.recipiente = recipiente;
    }

    public String getSabores() {
        return sabores;
    }

    public void setSabores(String sabores) {
        this.sabores = sabores;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
