package com.teamcode.creamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamcode.creamy.Models.IceCream;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Locale;

public class activity_shopping_car extends AppCompatActivity {

    private ListView listView;
    private ShoppingCarAdapter shoppingCarAdapter;
    private Button btnPedido;
    private TextView tvTotalOrder;
    private ArrayList<IceCream> iceCreams;

    FirebaseAuth myAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser user;

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        btnPedido = findViewById(R.id.btnPedido);
        listView = (ListView) findViewById(R.id.listViewIceCream);
        iceCreams = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        myAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        getArrayItems();

    }

    private void loadListView()
    {
        shoppingCarAdapter = new ShoppingCarAdapter(activity_shopping_car.this, iceCreams);
        listView.setAdapter(shoppingCarAdapter);
        tvTotalOrder.setText("Total Bs "+getTotalOrder());
        shoppingCarAdapter.setCustomEventListener(new ShoppingCarAdapter.OnCustomEventListener() {
            @Override
            public void onEvent(IceCream iceCream) {
                deleteArrayItem(iceCream);
                loadListView();
            }
        });
    }

    private void deleteArrayItem(IceCream iceCream)
    {
        iceCreams.remove(iceCream);
    }



    private void getArrayItems()
    {
        if(user != null)
        {
            mDatabase.child("user").child(user.getUid()).child("shoppingCart").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for (DataSnapshot ds: snapshot.getChildren()){
                            String container = ds.child("container").getValue().toString();
                            String flavor = ds.child("flavor").getValue().toString();
                            Double price = Double.parseDouble(ds.child("price").getValue().toString());
                            iceCreams.add(new IceCream(getRecipíente(container),container,flavor, price));
                        }
                    }
                    loadListView();
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Getting Post failed, log a message
                    Log.e("Error", "loadPost:onCancelled", error.toException());
                }
            });
        }
    }

    private double getTotalOrder()
    {
        double total = 0;
        for (IceCream iceCream: iceCreams) {
            total += iceCream.getPrecio();
        }
        return total;
    }

    public int getRecipíente(String typeRecipiente)
    {
        int indice = 0;
        switch (typeRecipiente.toLowerCase(Locale.ROOT))
        {
            case "cono":
                    indice = R.drawable.ice_cream_cone;
                break;
            case "vaso":
                indice = R.drawable.ice_cream_cup;
                break;
            case "plato":
                indice = R.drawable.bgicecream;
                break;
        }
        return  indice;
    }
}