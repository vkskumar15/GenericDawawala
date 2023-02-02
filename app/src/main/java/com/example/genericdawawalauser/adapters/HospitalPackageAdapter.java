package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.DoctorListBinding;
import com.example.genericdawawalauser.databinding.ListPackagesBinding;

public class HospitalPackageAdapter  extends RecyclerView.Adapter<HospitalPackageAdapter.MyViewHolder> {
    @NonNull
    @Override
    public HospitalPackageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalPackageAdapter.MyViewHolder(ListPackagesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalPackageAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ListPackagesBinding binding ;
        public MyViewHolder(@NonNull ListPackagesBinding itemView) {
            super(itemView.getRoot());


            binding = itemView ;
        }
    }
}
