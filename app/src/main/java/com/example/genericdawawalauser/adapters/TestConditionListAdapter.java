package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.ListPartentPackagesBinding;
import com.example.genericdawawalauser.databinding.TestConditionsItemsBinding;

public class TestConditionListAdapter extends RecyclerView.Adapter<TestConditionListAdapter.TestConditionViewHolder> {
    @NonNull
    @Override
    public TestConditionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestConditionsItemsBinding testConditionsItemsBinding = TestConditionsItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TestConditionViewHolder(testConditionsItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestConditionViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class TestConditionViewHolder extends RecyclerView.ViewHolder {
        private TestConditionsItemsBinding binding;

        public TestConditionViewHolder(@NonNull TestConditionsItemsBinding testConditionsItemsBinding) {
            super(testConditionsItemsBinding.getRoot());
            binding = testConditionsItemsBinding;
        }

        public void bind() {
            ConditionSymptomListAdapter conditionSymptomListAdapter = new ConditionSymptomListAdapter();
            binding.testConditionSymptomNestedRecyclerView.setAdapter(conditionSymptomListAdapter);
        }
    }
}
