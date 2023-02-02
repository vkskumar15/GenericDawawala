package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.DoctorListBinding;
import com.example.genericdawawalauser.databinding.HealthcareProductsBinding;

public class HealthcareProductsAdapter extends RecyclerView.Adapter<HealthcareProductsAdapter.MyViewHolder> {
    @NonNull
    @Override
    public HealthcareProductsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HealthcareProductsAdapter.MyViewHolder(HealthcareProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HealthcareProductsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        HealthcareProductsBinding binding;
        public MyViewHolder(@NonNull HealthcareProductsBinding  itemView) {
            super(itemView.getRoot());

            binding = itemView ;
        }
    }
}
