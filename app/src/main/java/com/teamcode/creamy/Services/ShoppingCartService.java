package com.teamcode.creamy.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcode.creamy.Models.IceCream;
import com.teamcode.creamy.Interfaces.Observable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService extends Observable {
    private DatabaseReference mDatabase;

    public ShoppingCartService() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addIceCreamToCar(List<IceCream> iceCreams, String idUser) {
        mDatabase.child("user").child(idUser).child("shoppingCart").setValue(iceCreams);
    }

    public void getCart(String userId) {
        ArrayList<IceCream> cart = new ArrayList<>();
        mDatabase.child("user").child(userId).child("shoppingCart").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for (DataSnapshot snapshot: task.getResult().getChildren()) {
                        IceCream iceCream = new IceCream(
                            String.valueOf(snapshot.child("flavor").getValue()),
                            String.valueOf(snapshot.child("container").getValue()),
                            Double.parseDouble(String.valueOf(snapshot.child("price").getValue()))
                        );

                        cart.add(iceCream);
                    }
                    notifySuscribers(cart);
                }
            }
        });
    }

}
