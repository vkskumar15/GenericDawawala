package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;


public class AllMedicinesAdapter extends RecyclerView.Adapter<AllMedicinesAdapter.MedicineViewHolder> {

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_medicine_item, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind() {

        }
    }
}
