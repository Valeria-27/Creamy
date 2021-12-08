package com.teamcode.creamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamcode.creamy.Models.IceCream;

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCarAdapter extends BaseAdapter {

    public interface OnCustomEventListener{
        public void onEvent(IceCream iceCream);   //method, which can have parameters
    }
    private OnCustomEventListener mListener; //listener field
    private Context context;
    private ArrayList<IceCream> creamArrayList;

    public void setCustomEventListener(OnCustomEventListener eventListener) {
        this.mListener=eventListener;
    }

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
        Button btnDelete = view.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Toast.makeText(context, "Holaaaa" + iceCream.getPrecio(), Toast.LENGTH_SHORT).show();
                deleteItem(iceCream);*/
                if(mListener!=null){
                    mListener.onEvent(iceCream);
                }
            }
        });

        imgFoto.setImageResource(iceCream.getImgFoto());
        tvRecipiente.setText("Recipiente: " + iceCream.getRecipiente());
        tvSabores.setText("Sabores: " + iceCream.getSabores());
        tvPrecio.setText("Bs " + iceCream.getPrecio());
        return view;
    }

    public void deleteItem(IceCream iceCream)
    {
        creamArrayList.remove(iceCream);
    }



}
