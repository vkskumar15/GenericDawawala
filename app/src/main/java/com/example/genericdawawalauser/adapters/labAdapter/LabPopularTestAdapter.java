package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.LabPackageLayoutBinding;
import com.example.genericdawawalauser.databinding.PopularLabTestLayoutBinding;

public class LabPopularTestAdapter extends RecyclerView.Adapter<LabPopularTestAdapter.ViewHolder> {

    @NonNull
    @Override
    public LabPopularTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPopularTestAdapter.ViewHolder(PopularLabTestLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPopularTestAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PopularLabTestLayoutBinding binding;

        public ViewHolder(@NonNull PopularLabTestLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
