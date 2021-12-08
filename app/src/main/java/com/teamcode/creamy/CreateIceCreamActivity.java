package com.teamcode.creamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teamcode.creamy.Models.IceCream;
import com.teamcode.creamy.Models.User;
import com.teamcode.creamy.Services.ShoppingCartService;

public class CreateIceCreamActivity extends AppCompatActivity {
    Button btnCreateIceCream;
    IceCream iceCream;
    User user;
    ShoppingCartService carService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ice_cream);

        btnCreateIceCream = findViewById(R.id.btnCreateIceCream);
        carService = new ShoppingCartService();
        iceCream = new IceCream();
        btnCreateIceCream.setOnClickListener(this::onClickAddIceCreamToCart);
    }

    public void onClickAddIceCreamToCart(View view) {
        iceCream.setContainer("Vaso");
        iceCream.setFlavor("Oreo, Limón, Maracuyá");
        iceCream.setPrice(21.0);

        IceCream iceCream1 = new IceCream();
        iceCream1.setContainer("Cono");
        iceCream1.setFlavor("Frutilla");
        iceCream1.setPrice(11.0);

        user = new User("M2uTRcV9zzSnWDs62CJGesVfSKs2");
        user.getCarrito().add(iceCream);
        user.getCarrito().add(iceCream1);

        carService.addIceCreamToCar( user.getCarrito(), user.getIdUser());
    }

}