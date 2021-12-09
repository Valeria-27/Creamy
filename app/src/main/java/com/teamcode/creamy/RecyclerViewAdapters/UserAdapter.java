package com.teamcode.creamy.RecyclerViewAdapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamcode.creamy.Models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderDatos> {
    private ArrayList<User> arrayListUser;

    public UserAdapter() { }
    public UserAdapter(ArrayList<User> arrayListUser) {
        this.arrayListUser = arrayListUser;
    }



    @NonNull
    @Override
    public UserAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolderDatos holder, int position) {
        holder.assignUser(arrayListUser.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

        }

        public void assignUser(User user) {
        }
    }
}
