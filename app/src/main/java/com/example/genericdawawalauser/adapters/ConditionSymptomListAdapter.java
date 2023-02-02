package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.NestedTestItemsBinding;

public class ConditionSymptomListAdapter extends RecyclerView.Adapter<ConditionSymptomListAdapter.ConditionSymptomViewHolder> {

    @NonNull
    @Override
    public ConditionSymptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NestedTestItemsBinding nestedTestItemsBinding = NestedTestItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ConditionSymptomViewHolder(nestedTestItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionSymptomViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ConditionSymptomViewHolder extends RecyclerView.ViewHolder {
        private NestedTestItemsBinding binding;

        public ConditionSymptomViewHolder(@NonNull NestedTestItemsBinding nestedTestItemsBinding) {
            super(nestedTestItemsBinding.getRoot());
            binding = nestedTestItemsBinding;
        }

        public void bind() {

        }
    }
}
