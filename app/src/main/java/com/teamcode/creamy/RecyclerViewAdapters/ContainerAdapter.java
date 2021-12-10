package com.teamcode.creamy.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.teamcode.creamy.Helpers.ImageFromUrl;
import com.teamcode.creamy.Models.Container;
import com.teamcode.creamy.R;

import java.util.List;

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ContainerHolder> {
    private List<Container> containers;

    class ContainerHolder extends RecyclerView.ViewHolder {
        TextView tvPrice;
        ImageView containerImg;
        ContainerHolder(View view) {
            super(view);
            tvPrice = view.findViewById(R.id.tvPrice);
            containerImg = view.findViewById(R.id.containerImage);
        }
    }

    public ContainerAdapter(List<Container> containers) {
        this.containers = containers;
    }

    @NonNull
    @Override
    public ContainerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_list_item_layout, parent, false);
        return new ContainerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContainerHolder holder, int position) {
        Container container = containers.get(position);
//        holder.containerImg.setImageResource(container.getImageId());
        new ImageFromUrl(holder.containerImg).execute(container.getImageUrl());
        holder.tvPrice.setText(String.format("Bs. %s", String.valueOf(container.getPrice())));
    }

    @Override
    public int getItemCount() {
        return containers.size();
    }
}
