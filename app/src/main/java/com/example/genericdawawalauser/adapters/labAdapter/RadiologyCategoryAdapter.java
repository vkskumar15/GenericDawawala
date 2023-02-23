package com.example.genericdawawalauser.adapters.labAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.databinding.RadiologyCategoryLayoutBinding;


public class RadiologyCategoryAdapter extends RecyclerView.Adapter<RadiologyCategoryAdapter.MedicineViewHolder> {

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RadiologyCategoryAdapter.MedicineViewHolder(RadiologyCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {

        RadiologyCategoryLayoutBinding binding;

        public MedicineViewHolder(@NonNull RadiologyCategoryLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }



    }
}
