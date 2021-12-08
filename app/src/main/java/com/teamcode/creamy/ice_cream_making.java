package com.teamcode.creamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        comboSecondFlavour = findViewById(R.id.spinnerSecondFlavor);
        comboThreeFlavour = findViewById(R.id.spinnerThreeFlavor);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        loadContainers();
        loadFlavor();

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
        ArrayList<Flavor> flavors = new ArrayList<>();

        mDatabase.child("flavors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String name = ds.child("name").getValue().toString();
                        flavors.add(new Flavor(id,name));

                    }
                    ArrayAdapter<Flavor> arrayAdapter = new ArrayAdapter<>(ice_cream_making.this, android.R.layout.simple_spinner_dropdown_item, flavors);

                    comboFirtsFlavour.setAdapter(arrayAdapter);
                    comboSecondFlavour.setAdapter(arrayAdapter);
                    comboThreeFlavour.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message
                Log.w("ERROROROROROR", "loadPost:onCancelled", error.toException());
            }
        });


    }



}