package com.teamcode.creamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.teamcode.creamy.Interfaces.IObserver;
import com.teamcode.creamy.Models.IceCream;
import com.teamcode.creamy.Models.User;
import com.teamcode.creamy.Services.AuthService;
import com.teamcode.creamy.Services.IceCreamService;
import com.teamcode.creamy.Services.ShoppingCartService;
import com.teamcode.creamy.Models.Container;

import java.util.ArrayList;
import java.util.List;

public class CreateIceCreamActivity extends AppCompatActivity implements IObserver {
    Spinner comboContainer, comboFirtsFlavour, comboSecondFlavour,comboThreeFlavour;
    ArrayList<Flavor> flavors;
    List<Container> containers;
    Button btnCreateIceCream;
    IceCream iceCream;
    User user;
    ShoppingCartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ice_cream);

        AuthService authService = new AuthService();
        authService.suscribe(this);
        authService.getCurrentUser();

        IceCreamService iceCreamService = new IceCreamService();
        iceCreamService.suscribe(this);

        cartService = new ShoppingCartService();

        flavors = new ArrayList<>();
        containers = new ArrayList<>();

        comboContainer = findViewById(R.id.spinnerContainers);
        comboFirtsFlavour = findViewById(R.id.spinnerFirstFlavor);
        comboSecondFlavour = findViewById(R.id.spinnerSecondFlavor);
        comboThreeFlavour = findViewById(R.id.spinnerThreeFlavor);
        btnCreateIceCream = findViewById(R.id.btnCreateIceCream);

        btnCreateIceCream.setOnClickListener(this::onClickAddIceCreamToCart);

        iceCreamService.getContainers();
        iceCreamService.getFlavors();
    }

    public void loadContainers(){
        ArrayAdapter<Container> arrayAdapter = new ArrayAdapter<>(
                CreateIceCreamActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                containers
        );
        comboContainer.setAdapter(arrayAdapter);
    }

    public void loadFlavors(){
        ArrayAdapter<Flavor> arrayAdapter = new ArrayAdapter<>(
                CreateIceCreamActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                flavors
        );

        comboFirtsFlavour.setAdapter(arrayAdapter);
        comboSecondFlavour.setAdapter(arrayAdapter);
        comboThreeFlavour.setAdapter(arrayAdapter);
    }

    public void onClickAddIceCreamToCart(View view) {
        iceCream = new IceCream();
        Container c = (Container) comboContainer.getSelectedItem();
        double priceContainer = 0;
        priceContainer = c.getPrice();
        iceCream.setContainer(comboContainer.getSelectedItem().toString());
        double h1,h2,h3;
        if (comboFirtsFlavour.getSelectedItem().toString().equals("Ningún sabor")) {
            h1 = 0;
        } else {
            h1 = 3.5;
        }

        if (comboSecondFlavour.getSelectedItem().toString().equals("Ningún sabor")) {
            h2 = 0;
        } else {
            h2 = 3.5;
        }

        if (comboThreeFlavour.getSelectedItem().toString().equals("Ningún sabor")) {
            h3 = 0;
        } else {
            h3 = 3.5;
        }

        double t;
        t = h1 + h2 + h3 + priceContainer;
        iceCream.setFlavor(comboFirtsFlavour.getSelectedItem().toString() + ", "+ comboSecondFlavour.getSelectedItem().toString() + ", " + comboThreeFlavour.getSelectedItem().toString());
        iceCream.setPrice(t);
        user.getCarrito().add(iceCream);
        cartService.addIceCreamToCar( user.getCarrito(), user.getId());
        Toast.makeText(this, "Helado agregado al carrito!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void update(Object value) {
        if(value instanceof java.util.ArrayList) {
            if(((ArrayList<?>)value).get(0) instanceof com.teamcode.creamy.Models.Flavor) {
                this.flavors = (ArrayList<Flavor>) value;
                loadFlavors();
            }
            else if (((ArrayList<?>)value).get(0) instanceof com.teamcode.creamy.Models.Container) {
                this.containers = (ArrayList<Container>) value;
                loadContainers();
            }
        }
        else if(value instanceof com.teamcode.creamy.Models.User) {
            this.user = (User) value;
        }
    }

}