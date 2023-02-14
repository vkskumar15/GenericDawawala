package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.LabPackageLayoutBinding;
import com.example.genericdawawalauser.databinding.PopularLabTestLayoutBinding;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;

import java.util.List;

public class LabPopularTestAdapter extends RecyclerView.Adapter<LabPopularTestAdapter.ViewHolder> {

    List<MedicineDataModal.Detail> list;
    private Context context;
    DetailsData details;
    AddtoCart addtoCart;

    public interface DetailsData {
        void details(MedicineDataModal.Detail detail);
    }

    public interface AddtoCart {
        void addToCart(MedicineDataModal.Detail detail);
    }


    public LabPopularTestAdapter(List<MedicineDataModal.Detail> list, Context context, DetailsData details, AddtoCart addtoCart) {
        this.list = list;
        this.context = context;
        this.details = details;
        this.addtoCart = addtoCart;
    }

    @NonNull
    @Override
    public LabPopularTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabPopularTestAdapter.ViewHolder(PopularLabTestLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabPopularTestAdapter.ViewHolder holder, int position) {

        holder.binding.medName.setText(list.get(position).getTitle());
        holder.binding.medPrice.setText("₹ "+list.get(position).getPrice()+"/-");

        holder.itemView.setOnClickListener(v -> {

            details.details(list.get(position));

        });


        holder.binding.addCart.setOnClickListener(v -> {

            addtoCart.addToCart(list.get(position));

        });

        if (list.get(position).getAddToCartStatus().equalsIgnoreCase("1"))
        {
            holder.binding.addCart.setVisibility(View.INVISIBLE);
            holder.binding.delete.setVisibility(View.VISIBLE);
            holder.binding.addedInCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PopularLabTestLayoutBinding binding;

        public ViewHolder(@NonNull PopularLabTestLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
