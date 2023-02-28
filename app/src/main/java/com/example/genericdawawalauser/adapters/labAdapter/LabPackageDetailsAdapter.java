package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.databinding.LabDeatilsLayoutBinding;
import com.example.genericdawawalauser.modalClass.GetUserLabModal;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;
import com.example.genericdawawalauser.modalClass.LabPackageDetailsModal;

import java.util.ArrayList;
import java.util.List;

public class LabPackageDetailsAdapter extends RecyclerView.Adapter<LabPackageDetailsAdapter.ViewHolder> {
    List<GetUserLabModal.Detail> list;
    Context context;
    SelectLab selectLab;
    private List<GetUserLabModal.Detail.Test> labTestLists = new ArrayList<>();
    LabTestPackageAdapter adapter;

    public interface SelectLab {

        void selectLab(GetUserLabModal.Detail detail);
    }

    public LabPackageDetailsAdapter(List<GetUserLabModal.Detail> list, Context context, SelectLab selectLab) {
        this.list = list;
        this.context = context;
        this.selectLab = selectLab;
    }

    @NonNull
    @Override
    public LabPackageDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPackageDetailsAdapter.ViewHolder(LabDeatilsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPackageDetailsAdapter.ViewHolder holder, int position) {

        labTestLists = list.get(position).getTest();
        adapter = new LabTestPackageAdapter(labTestLists, context);
        holder.binding.labtestsRv.setAdapter(adapter);


        holder.binding.name.setText(list.get(position).getName());
        holder.binding.discount.setText(String.valueOf("₹ "+list.get(position).getActualPrice()));
        holder.binding.off.setText(String.valueOf(list.get(position).getDiscount()+"%OFF"));
        holder.binding.price.setText(String.valueOf("₹ " + list.get(position).getTotalPrice() + "/-"));
        Glide.with(context).load(list.get(position).getImage()).into(holder.binding.profileImage);


        holder.binding.selectBtn.setOnClickListener(v -> {

            selectLab.selectLab(list.get(position));

        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LabDeatilsLayoutBinding binding;

        public ViewHolder(@NonNull LabDeatilsLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
