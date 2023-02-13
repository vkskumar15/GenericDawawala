package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.PatientLayoutBinding;

public class LabPopularCategoryAdapter extends RecyclerView.Adapter<LabPopularCategoryAdapter.ViewHolder> {

    @NonNull
    @Override
    public LabPopularCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPopularCategoryAdapter.ViewHolder(PatientLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPopularCategoryAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PatientLayoutBinding binding;

        public ViewHolder(@NonNull PatientLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
