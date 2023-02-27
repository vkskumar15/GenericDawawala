package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.AddPatientDetailsBinding;
import com.example.genericdawawalauser.databinding.ListMedicineBinding;
import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.databinding.RadiologyTestLayoutBinding;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;

import java.util.List;

public class RadiologyTestDetailsAdapter extends RecyclerView.Adapter<RadiologyTestDetailsAdapter.MedicineViewHolder> {

    List<RadiologyPackageTestModal.Detail.Test> list;
    Context context;

    public RadiologyTestDetailsAdapter(List<RadiologyPackageTestModal.Detail.Test> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(RadiologyTestLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {

        holder.binding.testName.setText(list.get(position).getTestname());
        holder.binding.testCount.setText(String.format("%d: ", position + 1));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {

        RadiologyTestLayoutBinding binding;

        public MedicineViewHolder(@NonNull RadiologyTestLayoutBinding listMedicineBinding) {
            super(listMedicineBinding.getRoot());
            binding = listMedicineBinding;
        }

    }
}
