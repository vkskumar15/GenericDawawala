package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.AddPatientDetailsBinding;
import com.example.genericdawawalauser.modalClass.GetFamilyMemberModal;

import java.util.List;


public class FamilyMemberAdapter extends RecyclerView.Adapter<FamilyMemberAdapter.ViewHolder> {

    List<GetFamilyMemberModal.Detail> list;
    private Context context;
    SelectPatient delete;

    public  interface SelectPatient{

        void selectPatient(GetFamilyMemberModal.Detail detail);
        void editData(GetFamilyMemberModal.Detail detail);
    }

    public FamilyMemberAdapter(List<GetFamilyMemberModal.Detail> list, Context context, SelectPatient delete) {
        this.list = list;
        this.context = context;
        this.delete = delete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FamilyMemberAdapter.ViewHolder(AddPatientDetailsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.txtName.setText(list.get(position).getName());
        holder.binding.txtAddres.setText(list.get(position).getGender());
        holder.binding.txtPhoneNo.setText(list.get(position).getRelation());
        holder.binding.linearLayRemoveAddress.setOnClickListener(v -> {

            delete.selectPatient(list.get(position));
        });

        holder.binding.linearLayEditAddress.setOnClickListener(v -> {

            delete.editData(list.get(position));
        });
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
