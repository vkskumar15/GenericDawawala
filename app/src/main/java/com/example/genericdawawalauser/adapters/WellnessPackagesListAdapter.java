package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.ListWellnessPackagesBinding;

public class WellnessPackagesListAdapter extends RecyclerView.Adapter<WellnessPackagesListAdapter.WellnessPackagesViewHolder> {

    @NonNull
    @Override
    public WellnessPackagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListWellnessPackagesBinding listWellnessPackagesBinding = ListWellnessPackagesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WellnessPackagesViewHolder(listWellnessPackagesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WellnessPackagesViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class WellnessPackagesViewHolder extends RecyclerView.ViewHolder {
        private ListWellnessPackagesBinding binding = null;

        public WellnessPackagesViewHolder(@NonNull ListWellnessPackagesBinding listWellnessPackagesBinding) {
            super(listWellnessPackagesBinding.getRoot());
            binding = listWellnessPackagesBinding;
        }

        public void bind() {



        }
    }
}
