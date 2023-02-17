package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.CartItemBinding;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddToCartAdapter.ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartItemBinding binding;

        public ViewHolder(@NonNull CartItemBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
