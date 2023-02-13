package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.FeaturedPackagesLayoutBinding;
import com.example.genericdawawalauser.databinding.PopularLabTestLayoutBinding;

public class RadiologyLabTestAdapter extends RecyclerView.Adapter<RadiologyLabTestAdapter.ViewHolder> {

    @NonNull
    @Override
    public RadiologyLabTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RadiologyLabTestAdapter.ViewHolder(FeaturedPackagesLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RadiologyLabTestAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FeaturedPackagesLayoutBinding binding;

        public ViewHolder(@NonNull FeaturedPackagesLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
