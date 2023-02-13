package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.LabPackageLayoutBinding;

public class LabPackageAdapter extends RecyclerView.Adapter<LabPackageAdapter.ViewHolder> {

    @NonNull
    @Override
    public LabPackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPackageAdapter.ViewHolder(LabPackageLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPackageAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LabPackageLayoutBinding binding;

        public ViewHolder(@NonNull LabPackageLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
