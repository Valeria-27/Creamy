package com.teamcode.creamy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        btnPedido = findViewById(R.id.btnPedido);
        listView = (ListView) findViewById(R.id.listViewIceCream);
        iceCreams = new ArrayList<>();
        getArrayItems();
        loadListView();


    }

    private void loadListView()
    {
        shoppingCarAdapter = new ShoppingCarAdapter(activity_shopping_car.this, iceCreams);
        listView.setAdapter(shoppingCarAdapter);
        tvTotalOrder.setText("Total Bs "+getTotalOrder());
        shoppingCarAdapter.setCustomEventListener(new ShoppingCarAdapter.OnCustomEventListener() {
            @Override
            public void onEvent(IceCream iceCream) {
                Toast.makeText(activity_shopping_car.this, "Sin funciona" + iceCream.getPrecio(), Toast.LENGTH_SHORT).show();
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
        iceCreams = new ArrayList<>();
        iceCreams.add(new IceCream(getRecipíente("vazo"), "Vazo", "limon y fresa", 5));
        iceCreams.add(new IceCream(getRecipíente("vazo"), "Vazo", "Sandilla, chicle y Pera", 9));
        iceCreams.add(new IceCream(getRecipíente("cono"), "Cono", "limon y fresa", 5));
        iceCreams.add(new IceCream(getRecipíente("plato"), "Plato", "limon y fresa", 5));
        iceCreams.add(new IceCream(getRecipíente("vazo"), "Vazo", "limon y fresa", 5));
        iceCreams.add(new IceCream(getRecipíente("vazo"), "Vazo", "Sandilla, chicle y Pera", 9));
        iceCreams.add(new IceCream(getRecipíente("cono"), "Cono", "limon y fresa", 5));
        iceCreams.add(new IceCream(getRecipíente("plato"), "Plato", "limon y fresa", 5));

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
            case "vazo":
                indice = R.drawable.ice_cream_cup;
                break;
            case "plato":
                indice = R.drawable.bgicecream;
                break;
        }
        return  indice;
    }
}