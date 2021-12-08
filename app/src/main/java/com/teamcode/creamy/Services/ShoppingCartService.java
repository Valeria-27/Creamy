package com.teamcode.creamy.Services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcode.creamy.Models.IceCream;
import com.teamcode.creamy.Models.User;

import java.util.List;

public class ShoppingCartService {
    private DatabaseReference mDatabase;

    public ShoppingCartService() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addIceCreamToCar(List<IceCream> iceCreams, String idUser) {
        mDatabase.child("user").child(idUser).child("shoppingCart").setValue(iceCreams);
    }

}
