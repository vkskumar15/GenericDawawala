package com.example.genericdawawalauser.adapters.labAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.modalClass.LabTestCategories;

import java.util.List;

public class LabPopularCategoryAdapter extends RecyclerView.Adapter<LabPopularCategoryAdapter.ViewHolder> {
    List<LabTestCategories.Detail> details;
    private Context context;
    ClickLab clickLab;
    private int rowIndex = -1;

    public LabPopularCategoryAdapter(List<LabTestCategories.Detail> details, Context context, ClickLab clickLab) {
        this.details = details;
        this.context = context;
        this.clickLab = clickLab;
    }

    public interface ClickLab {
        void clickLab(LabTestCategories.Detail detail);
    }

    @NonNull
    @Override
    public LabPopularCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPopularCategoryAdapter.ViewHolder(PatientLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPopularCategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.testName.setText(details.get(position).getTitle());

        holder.binding.card.setOnClickListener(v -> {
            rowIndex = position;
            clickLab.clickLab(details.get(position));
            notifyDataSetChanged();

        });


        if (rowIndex == position) {

            holder.binding.card.setBackgroundResource(R.drawable.outline_select_slot_address);
            holder.binding.testName.setTextColor(Color.WHITE);

        } else {

            holder.binding.testName.setTextColor(Color.BLACK);
            holder.binding.card.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }

    }

    @Override
    public int getItemCount() {

        return details.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PatientLayoutBinding binding;

        public ViewHolder(@NonNull PatientLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
