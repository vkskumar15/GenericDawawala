package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.HomeHealthcareItemsBinding;
import com.example.genericdawawalauser.databinding.SlideBinding;

public class AdapterHomeHealthCare extends RecyclerView.Adapter<AdapterHomeHealthCare.MyViewHolder> {


    @NonNull
    @Override
    public AdapterHomeHealthCare.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterHomeHealthCare.MyViewHolder(HomeHealthcareItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeHealthCare.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        HomeHealthcareItemsBinding binding ;
        public MyViewHolder(@NonNull HomeHealthcareItemsBinding itemView) {
            super(itemView.getRoot());


            binding = itemView ;
        }
    }
}
