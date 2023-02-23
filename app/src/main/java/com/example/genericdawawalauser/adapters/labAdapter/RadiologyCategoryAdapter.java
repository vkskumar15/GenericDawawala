package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.databinding.RadiologyCategoryLayoutBinding;
import com.example.genericdawawalauser.modalClass.RadiologyCategoryModal;

import java.util.List;


public class RadiologyCategoryAdapter extends RecyclerView.Adapter<RadiologyCategoryAdapter.MedicineViewHolder> {

    List<RadiologyCategoryModal.Detail> list;
    Context context;
    SelectCategory category;

    public interface SelectCategory {

        void category(RadiologyCategoryModal.Detail detail);
    }

    public RadiologyCategoryAdapter(List<RadiologyCategoryModal.Detail> list, Context context, SelectCategory category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RadiologyCategoryAdapter.MedicineViewHolder(RadiologyCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {

        holder.binding.categoryName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImage()).into(holder.binding.categoryImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {

        RadiologyCategoryLayoutBinding binding;

        public MedicineViewHolder(@NonNull RadiologyCategoryLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }


    }
}
