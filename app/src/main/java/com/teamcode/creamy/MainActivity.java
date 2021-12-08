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
import com.teamcode.creamy.Models.Container;
import com.teamcode.creamy.Models.ContainerType;
import com.teamcode.creamy.Models.Flavor;
import com.teamcode.creamy.RecyclerViewAdapters.ContainerAdapter;
import com.teamcode.creamy.RecyclerViewAdapters.FlavorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Container> containers;
    ArrayList<Flavor> flavors;
    RecyclerView rvContainers, rvFlavors;
    Button btnCreateIceCream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContainers = findViewById(R.id.rvContainers);
        rvFlavors = findViewById(R.id.rvFlavors);
        btnCreateIceCream = findViewById(R.id.btnTestAddIceCream);

        btnCreateIceCream.setOnClickListener(this::goToCreateIceCream);

        loadContainersData();
        loadFlavorsData();

        loadContainersView();
        loadFlavorsView();
    }

    private void loadContainersData() {
        containers = new ArrayList<Container>();
        containers.add(new Container(5.00, "Cono", R.drawable.ice_cream_cone));
        containers.add(new Container(4.00, "Vaso", R.drawable.ice_cream_cup));
    }

    private void loadFlavorsData() {
        flavors = new ArrayList<Flavor>();
        flavors.add(new Flavor("Limón", R.drawable.flavor_lemon));
        flavors.add(new Flavor("Oreo", R.drawable.flavor_oreo));
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}