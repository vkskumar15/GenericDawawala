package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.ListPrescriptionBinding;

public class UploadedPrescriptionsListAdapter extends RecyclerView.Adapter<UploadedPrescriptionsListAdapter.UploadedPrescriptionsViewHolder> {

    @NonNull
    @Override
    public UploadedPrescriptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPrescriptionBinding listPrescriptionBinding = ListPrescriptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UploadedPrescriptionsViewHolder(listPrescriptionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadedPrescriptionsViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class UploadedPrescriptionsViewHolder extends RecyclerView.ViewHolder {
        private ListPrescriptionBinding binding;

        public UploadedPrescriptionsViewHolder(@NonNull ListPrescriptionBinding listPrescriptionBinding) {
            super(listPrescriptionBinding.getRoot());
            binding = listPrescriptionBinding;
        }

        public void bind() {

        }
    }
}
