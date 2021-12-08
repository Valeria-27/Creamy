package com.teamcode.creamy.RecyclerViewAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.teamcode.creamy.Helpers.ImageFromUrl;
import com.teamcode.creamy.Models.Flavor;
import com.teamcode.creamy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlavorAdapter extends RecyclerView.Adapter<FlavorAdapter.FlavorHolder>{
    Context context;
    List<Flavor> flavors;
    ArrayList<Integer> colors;

    class FlavorHolder extends RecyclerView.ViewHolder {
        TextView tvFlavor;
        Drawable background;
        ImageView flavorImgView;
        FlavorHolder(View view) {
            super(view);
            tvFlavor = view.findViewById(R.id.tvFlavor);
            flavorImgView = view.findViewById(R.id.flavorImgView);
            background = flavorImgView.getBackground();
        }
    }

    public FlavorAdapter(Context context, List<Flavor> flavors) {
        this.context = context;
        this.flavors = flavors;
        this.colors = new ArrayList<>();
        colors.add(R.color.lemon_green);
        colors.add(R.color.oreo_yellow);
        colors.add(R.color.watermelon_green);
    }

    @NonNull
    @Override
    public FlavorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flavor_list_item_layout, parent, false);
        return new FlavorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavorHolder holder, int position) {
        Flavor flavor = flavors.get(position);
        holder.tvFlavor.setText(flavor.getName());
        GradientDrawable shapeDrawable = (GradientDrawable) holder.background;

        int colorIndex = new Random().nextInt(colors.size());
        shapeDrawable.setColor(ContextCompat.getColor(context, colors.get(colorIndex)));
//        holder.flavorImgView.setImageBitmap(image);
        new ImageFromUrl(holder.flavorImgView)
                .execute(flavor.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }


}
