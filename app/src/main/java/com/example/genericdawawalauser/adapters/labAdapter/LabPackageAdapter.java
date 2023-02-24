package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.LabPackageLayoutBinding;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;

import java.util.List;

public class LabPackageAdapter extends RecyclerView.Adapter<LabPackageAdapter.ViewHolder> {
    List<RadiologyPackageTestModal.Detail> list;
    Context context;
    SelectPackage selectPackage;

    public interface SelectPackage
    {
        void selectPackage(RadiologyPackageTestModal.Detail detail);
    }
    public LabPackageAdapter(List<RadiologyPackageTestModal.Detail> list, Context context, SelectPackage selectPackage) {
        this.list = list;
        this.context = context;
        this.selectPackage = selectPackage;
    }

    @NonNull
    @Override
    public LabPackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPackageAdapter.ViewHolder(LabPackageLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPackageAdapter.ViewHolder holder, int position) {

        holder.binding.packageName.setText("Tests Name: "+list.get(position).getTitle());
        holder.binding.text2.setText("Total Tests: "+String.valueOf(list.get(position).getTotalTests()));
        holder.itemView.setOnClickListener(view -> {

            selectPackage.selectPackage(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LabPackageLayoutBinding binding;

        public ViewHolder(@NonNull LabPackageLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
