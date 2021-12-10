package com.teamcode.creamy.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcode.creamy.Interfaces.Observable;
import com.teamcode.creamy.Models.Container;
import com.teamcode.creamy.Models.Flavor;

import java.util.ArrayList;
import java.util.List;

public class IceCreamService extends Observable {
    private DatabaseReference mDatabase;

    public IceCreamService() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void getContainers() {
        ArrayList<Container> containers = new ArrayList<>();
        mDatabase.child("containers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for (DataSnapshot snapshot: task.getResult().getChildren()) {
                        Container container = new Container(
                                Double.parseDouble(String.valueOf(snapshot.child("price").getValue())),
                                String.valueOf(snapshot.child("name").getValue()),
                                String.valueOf(snapshot.child("picture").getValue())
                        );

                        containers.add(container);
                    }
                    notifySuscribers(containers);
                }
            }
        });
    }

    public void getFlavors() {
        ArrayList<Flavor> flavors = new ArrayList<>();
        mDatabase.child("flavors").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for (DataSnapshot snapshot: task.getResult().getChildren()) {
                        Flavor flavor = new Flavor(
                            String.valueOf(snapshot.child("name").getValue()),
                            String.valueOf(snapshot.child("picture").getValue())
                        );

                        flavors.add(flavor);
                    }
                    notifySuscribers(flavors);
                }
            }
        });
    }



}
