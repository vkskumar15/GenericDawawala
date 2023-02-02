package com.example.genericdawawalauser.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.ListMedicineBinding;

import java.util.List;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder> {

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(ListMedicineBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {
        private ListMedicineBinding binding;

        public MedicineViewHolder(@NonNull ListMedicineBinding listMedicineBinding) {
            super(listMedicineBinding.getRoot());
            binding = listMedicineBinding;
        }

        public void bind() {
            binding.productImgV.setImageResource(R.drawable.ic_drugs_capsules_and_pills);
        }
    }
}
