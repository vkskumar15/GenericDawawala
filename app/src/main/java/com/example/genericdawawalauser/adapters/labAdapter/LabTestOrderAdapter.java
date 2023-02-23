package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.CartItemBinding;
import com.example.genericdawawalauser.modalClass.AddCartLabModal;

import java.util.List;

public class LabTestOrderAdapter extends RecyclerView.Adapter<LabTestOrderAdapter.ViewHolder> {
    List<AddCartLabModal.Detail> list;
    private Context context;


    public LabTestOrderAdapter(List<AddCartLabModal.Detail> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public LabTestOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LabTestOrderAdapter.ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabTestOrderAdapter.ViewHolder holder, int position) {

        holder.binding.medName.setText(list.get(position).getTitle());
        holder.binding.medPrice.setText("₹ "+String.valueOf(list.get(position).getTotalPrice())+"/-");
        holder.binding.labName.setText(list.get(position).getAboutTest());
        holder.binding.off.setText(list.get(position).getDiscount()+" %OFF");

        holder.binding.delete.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartItemBinding binding;

        public ViewHolder(@NonNull CartItemBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
