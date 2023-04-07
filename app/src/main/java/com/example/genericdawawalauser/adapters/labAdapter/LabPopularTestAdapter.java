package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    DeletetoCart deletetoCart;

    public interface DetailsData {

        void details(MedicineDataModal.Detail detail);

    }

    public interface DeletetoCart {

        void deletetoCart(MedicineDataModal.Detail detail);
    }

    public interface AddtoCart {

        void addToCart(MedicineDataModal.Detail detail);
    }


    public LabPopularTestAdapter(List<MedicineDataModal.Detail> list, Context context, DetailsData details, AddtoCart addtoCart, DeletetoCart deletetoCart) {
        this.list = list;
        this.context = context;
        this.details = details;
        this.addtoCart = addtoCart;
        this.deletetoCart = deletetoCart;
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
            notifyDataSetChanged();

        });

        holder.binding.delete.setOnClickListener(v -> {

            deletetoCart.deletetoCart(list.get(position));
        });

        if (list.get(position).getAddToCartStatus().equalsIgnoreCase("1"))
        {
            holder.binding.addCart.setVisibility(View.INVISIBLE);
            holder.binding.delete.setVisibility(View.VISIBLE);
            holder.binding.addedInCart.setVisibility(View.VISIBLE);
        }


        if (list.get(position).getHomeCollectionCheck().equalsIgnoreCase("0")) {

            holder.binding.sample.setText("Center In Visit");

        } else {

            holder.binding.sample.setText("Home Sample Collection");

        }
        if (list.get(position).getPrescriptionCheck().equalsIgnoreCase("0")) {

            holder.binding.preparation.setText("Preparation Required");

        } else {
            holder.binding.preparation.setText("Preparation Not Required");
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
