package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.LabPackageLayoutBinding;
import com.example.genericdawawalauser.databinding.TestConditionLayoutBinding;

public class LabTestByConditionAdapter extends RecyclerView.Adapter<LabTestByConditionAdapter.ViewHolder> {

    @NonNull
    @Override
    public LabTestByConditionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabTestByConditionAdapter.ViewHolder(TestConditionLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabTestByConditionAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TestConditionLayoutBinding binding;

        public ViewHolder(@NonNull TestConditionLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
