package com.teamcode.creamy.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcode.creamy.Models.Container;
import com.teamcode.creamy.Models.Flavor;

import java.util.List;

public class IceCreamService {
    private DatabaseReference mDatabase;

    public IceCreamService() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public List<Container> getContainers() {
        return null;
    }

    public List<Flavor> getFlavors() {
        mDatabase.child("flavors").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        return null;
    }

}
