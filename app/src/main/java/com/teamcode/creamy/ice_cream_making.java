package com.teamcode.creamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ice_cream_making extends AppCompatActivity {
    Spinner comboContainer, comboFirtsFlavour, comboSecondFlavour,comboThreeFlavour;

    //realtime reference

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_cream_making);
        comboContainer = findViewById(R.id.spinnerContainers);
        comboFirtsFlavour = findViewById(R.id.spinnerFirstFlavor);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        loadContainers();
        loadFlavor();
        loadSecondFlavour();
        loadThreeFlavour();
    }

    public void loadContainers(){
        List<Container> containers = new ArrayList<>();
        mDatabase.child("containers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        String id = ds.getKey();
                        String name = ds.child("name").getValue().toString();
                        containers.add(new Container(id,name));
                    }
                    ArrayAdapter<Container> arrayAdapter = new ArrayAdapter<>(ice_cream_making.this, android.R.layout.simple_dropdown_item_1line, containers);
                    comboContainer.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void loadFlavor(){
        List<Flavor> flavors = new ArrayList<>();
        mDatabase.child("flavors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String name = ds.child("name").getValue().toString();
                        flavors.add(new Flavor(id,name));
                    }
                    ArrayAdapter<Flavor> arrayAdapter = new ArrayAdapter<>(ice_cream_making.this, android.R.layout.simple_dropdown_item_1line, flavors);
                    comboFirtsFlavour.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadSecondFlavour(){
        List<Flavor> flavors = new ArrayList<>();
        mDatabase.child("flavors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String name = ds.child("name").getValue().toString();
                        flavors.add(new Flavor(id,name));
                    }
                    ArrayAdapter<Flavor> arrayAdapterTwo = new ArrayAdapter<>(ice_cream_making.this, android.R.layout.simple_dropdown_item_1line, flavors);
                    comboSecondFlavour.setAdapter(arrayAdapterTwo);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void loadThreeFlavour(){
        List<Flavor> flavors = new ArrayList<>();
        mDatabase.child("flavors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String name = ds.child("name").getValue().toString();
                        flavors.add(new Flavor(id,name));
                    }

                    ArrayAdapter<Flavor> arrayAdapterThree = new ArrayAdapter<>(ice_cream_making.this, android.R.layout.simple_dropdown_item_1line, flavors);
                    comboThreeFlavour.setAdapter(arrayAdapterThree);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}