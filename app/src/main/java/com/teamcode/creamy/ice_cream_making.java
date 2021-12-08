package com.teamcode.creamy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamcode.creamy.Interfaces.IObserver;
import com.teamcode.creamy.Models.IceCream;
import com.teamcode.creamy.Models.User;
import com.teamcode.creamy.Services.AuthService;
import com.teamcode.creamy.Services.IceCreamService;
import com.teamcode.creamy.Services.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;

public class ice_cream_making extends AppCompatActivity implements IObserver {
    Spinner comboContainer, comboFirtsFlavour, comboSecondFlavour,comboThreeFlavour;
    ArrayList<Flavor> flavors;
    List<Container> containers;
    Button btnCreateIceCream;
    IceCream iceCream;
    User user;
    ShoppingCartService carService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_cream_making);

        AuthService authService = new AuthService();
        authService.suscribe(this);
        authService.getCurrentUser();

        IceCreamService iceCreamService = new IceCreamService();
        iceCreamService.suscribe(this);

        flavors = new ArrayList<>();
        containers = new ArrayList<>();

        comboContainer = findViewById(R.id.spinnerContainers);
        comboFirtsFlavour = findViewById(R.id.spinnerFirstFlavor);
        comboSecondFlavour = findViewById(R.id.spinnerSecondFlavor);
        comboThreeFlavour = findViewById(R.id.spinnerThreeFlavor);

        iceCreamService.getContainers();
        iceCreamService.getFlavors();
    }

    public void loadContainers(){
        ArrayAdapter<Container> arrayAdapter = new ArrayAdapter<>(
            ice_cream_making.this,
            android.R.layout.simple_dropdown_item_1line,
            containers
        );
        comboContainer.setAdapter(arrayAdapter);
    }

    public void loadFlavors(){
        ArrayAdapter<Flavor> arrayAdapter = new ArrayAdapter<>(
            ice_cream_making.this,
            android.R.layout.simple_spinner_dropdown_item,
            flavors
        );

        comboFirtsFlavour.setAdapter(arrayAdapter);
        comboSecondFlavour.setAdapter(arrayAdapter);
        comboThreeFlavour.setAdapter(arrayAdapter);
    }

    public void onClickAddIceCreamToCart(View view) {
        iceCream.setContainer("Vaso");
        iceCream.setFlavor("Oreo, Limón, Maracuyá");
        iceCream.setPrice(21.0);

        user.getCarrito().add(iceCream);
        carService.addIceCreamToCar( user.getCarrito(), user.getId());
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