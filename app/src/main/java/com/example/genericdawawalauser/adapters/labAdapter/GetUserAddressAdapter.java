package com.example.genericdawawalauser.adapters.labAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.AddPatientDetailsBinding;
import com.example.genericdawawalauser.databinding.AddressesLayoutBinding;
import com.example.genericdawawalauser.modalClass.GetFamilyMemberModal;
import com.example.genericdawawalauser.modalClass.GetPatientAddress;

import java.util.List;


public class GetUserAddressAdapter extends RecyclerView.Adapter<GetUserAddressAdapter.ViewHolder> {

    List<GetPatientAddress.Detail> list;
    private Context context;
    SelectPatient delete;
    private int row_index = 0;

    public  interface SelectPatient{

        void selectPatient(GetPatientAddress.Detail detail);
        void editData(GetPatientAddress.Detail detail);
        void select(GetPatientAddress.Detail detail);
    }

    public GetUserAddressAdapter(List<GetPatientAddress.Detail> list, Context context, SelectPatient delete) {
        this.list = list;
        this.context = context;
        this.delete = delete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetUserAddressAdapter.ViewHolder(AddressesLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.txtName.setText(list.get(position).getName());
        holder.binding.txtAddres.setText(list.get(position).getFullAddress());
        holder.binding.txtPhoneNo.setText(list.get(position).getPhone());


        holder.binding.linearLayRemoveAddress.setOnClickListener(v -> {

            delete.selectPatient(list.get(position));
        });

        holder.binding.linearLayEditAddress.setOnClickListener(v -> {

            delete.editData(list.get(position));
        });

        holder.binding.radioButton.setChecked(false);

            holder.binding.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    row_index = position;

                    delete.select(list.get(position));

                    notifyDataSetChanged();

                }
            });

            if (row_index == 0) {

                holder.binding.radioButton.setChecked(false);

            } else if (row_index == position) {

                holder.binding.radioButton.setChecked(true);

            } else {

                holder.binding.radioButton.setChecked(false);

            }


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AddressesLayoutBinding binding;

        public ViewHolder(@NonNull AddressesLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }

    }
}
