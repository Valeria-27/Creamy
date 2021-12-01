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

import com.teamcode.creamy.Models.Container;
import com.teamcode.creamy.Models.ContainerSize;
import com.teamcode.creamy.Models.ContainerType;
import com.teamcode.creamy.Models.Flavor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Container> containers;
    ArrayList<Flavor> flavors;
    RecyclerView rvContainers, rvFlavors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContainers = findViewById(R.id.rvContainers);
        rvFlavors = findViewById(R.id.rvFlavors);

        loadContainersData();
        loadFlavorsData();

        loadContainersView();
        loadFlavorsView();
    }

    private void loadContainersData() {
        containers = new ArrayList<Container>();
        containers.add(new Container(5.00, ContainerType.cone, ContainerSize.regular, R.drawable.ice_cream_cone));
        containers.add(new Container(4.00, ContainerType.cup, ContainerSize.regular, R.drawable.ice_cream_cup));
    }

    private void loadFlavorsData() {
        flavors = new ArrayList<Flavor>();
        flavors.add(new Flavor("Limón", R.drawable.flavor_lemon));
        flavors.add(new Flavor("Oreo", R.drawable.flavor_oreo));
    }

    private void loadContainersView() {
        ContainerAdapter containerAdapter = new ContainerAdapter(containers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
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