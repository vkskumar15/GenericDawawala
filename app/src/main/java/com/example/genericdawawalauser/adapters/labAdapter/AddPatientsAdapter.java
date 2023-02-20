package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.AddPatientDetailsBinding;
import com.example.genericdawawalauser.modalClass.GetPatientAddress;

import java.util.List;


public class AddPatientsAdapter extends RecyclerView.Adapter<AddPatientsAdapter.ViewHolder> {

    List<GetPatientAddress.Detail> list;
    private Context context;
    SelectPatient selectPatient;

    public  interface SelectPatient{
        void selectPatient(GetPatientAddress.Detail detail);
    }

    public AddPatientsAdapter(List<GetPatientAddress.Detail> list, Context context, SelectPatient selectPatient) {
        this.list = list;
        this.context = context;
        this.selectPatient = selectPatient;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddPatientsAdapter.ViewHolder(AddPatientDetailsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AddPatientDetailsBinding binding;

        public ViewHolder(@NonNull AddPatientDetailsBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }

    }
}
