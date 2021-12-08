package com.teamcode.creamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.teamcode.creamy.Helpers.GridSpacingItemDecoration;
import com.teamcode.creamy.Interfaces.IObserver;
import com.teamcode.creamy.Models.Container;
import com.teamcode.creamy.Models.Flavor;
import com.teamcode.creamy.RecyclerViewAdapters.ContainerAdapter;
import com.teamcode.creamy.RecyclerViewAdapters.FlavorAdapter;
import com.teamcode.creamy.Services.IceCreamService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IObserver {
    IceCreamService iceCreamService;
    ArrayList<Container> containers;
    ArrayList<Flavor> flavors;
    RecyclerView rvContainers, rvFlavors;
    Button btnCreateIceCream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iceCreamService = new IceCreamService();
        iceCreamService.suscribe(this);

        rvContainers = findViewById(R.id.rvContainers);
        rvFlavors = findViewById(R.id.rvFlavors);
        btnCreateIceCream = findViewById(R.id.btnTestAddIceCream);

        btnCreateIceCream.setOnClickListener(this::goToCreateIceCream);

        iceCreamService.getFlavors();
        iceCreamService.getContainers();
    }

    private void loadContainersView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        ContainerAdapter containerAdapter = new ContainerAdapter(containers);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvContainers.setLayoutManager(layoutManager);
        rvContainers.setItemAnimator(new DefaultItemAnimator());
        rvContainers.setAdapter(containerAdapter);
    }

    private void loadFlavorsView() {
        int spanCount = 3; // 3 columns
        int spacing = 8; // 8px
        boolean includeEdge = false;
        rvFlavors.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        FlavorAdapter flavorAdapter = new FlavorAdapter(getApplicationContext(), flavors);
        rvFlavors.setLayoutManager(new GridLayoutManager(this, 3));
        rvFlavors.setItemAnimator(new DefaultItemAnimator());
        rvFlavors.setAdapter(flavorAdapter);
    }

    public void goToCreateIceCream(View view) {
        Intent intent = new Intent(MainActivity.this, CreateIceCreamActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.about:
                intent = new Intent(this, Activity_About.class);
                startActivity(intent);
                return true;
            case R.id.developers:
                intent = new Intent(this, DevelopersInfo.class);
                startActivity(intent);
                return true;
            case R.id.IceCream:
                intent = new Intent(this, CreateIceCreamActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void update(Object value) {
        if(value instanceof java.util.ArrayList) {
            if(((ArrayList<?>)value).get(0) instanceof Flavor) {
                this.flavors = (ArrayList<Flavor>) value;
                loadFlavorsView();
            }
            else if (((ArrayList<?>)value).get(0) instanceof Container) {
                this.containers = (ArrayList<Container>) value;
                loadContainersView();
            }
        }
    }
}