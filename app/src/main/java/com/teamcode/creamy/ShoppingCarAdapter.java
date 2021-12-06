package com.teamcode.creamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamcode.creamy.Models.IceCream;

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<IceCream> creamArrayList;

    public ShoppingCarAdapter(Context context, ArrayList<IceCream> creamArrayList) {
        this.context = context;
        this.creamArrayList = creamArrayList;
    }

    @Override
    public int getCount() {
        return creamArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return creamArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        IceCream iceCream = (IceCream) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.shopping_car_item, null);
        ImageView imgFoto = view.findViewById(R.id.ivFotoHelado);
        TextView tvRecipiente = view.findViewById(R.id.tvRecipiente);
        TextView tvSabores = view.findViewById(R.id.tvSabores);
        TextView tvPrecio = view.findViewById(R.id.tvPrecio);

        imgFoto.setImageResource(iceCream.getImgFoto());
        tvRecipiente.setText("Recipiente: " + iceCream.getRecipiente());
        tvSabores.setText("Sabores: " + iceCream.getSabores());
        tvPrecio.setText("Bs " + iceCream.getPrecio());
        return view;
    }



}
