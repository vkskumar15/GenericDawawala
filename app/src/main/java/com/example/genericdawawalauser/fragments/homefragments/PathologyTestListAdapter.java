package com.example.genericdawawalauser.fragments.homefragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.genericdawawalauser.databinding.ListPartentPackagesBinding;

import java.nio.file.Path;

public class
PathologyTestListAdapter extends RecyclerView.Adapter<PathologyTestListAdapter.PathologyTestViewHolder> {
    private ListPartentPackagesBinding listPartentPackagesBinding;

    @NonNull
    @Override
    public PathologyTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPartentPackagesBinding listPartentPackagesBinding = ListPartentPackagesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PathologyTestViewHolder(listPartentPackagesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PathologyTestViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class PathologyTestViewHolder extends RecyclerView.ViewHolder {
        private ListPartentPackagesBinding binding;

        public PathologyTestViewHolder(@NonNull ListPartentPackagesBinding listPartentPackagesBinding) {
            super(listPartentPackagesBinding.getRoot());
            binding = listPartentPackagesBinding;
        }

        public void bind() {

        }
    }

}
