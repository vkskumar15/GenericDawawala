package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.genericdawawalauser.databinding.CartItemBinding;
import com.example.genericdawawalauser.modalClass.AddCartLabModal;

import java.util.List;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {
    List<AddCartLabModal.Detail> list;
    private Context context;
    TotalDiscount discount;

    public interface TotalDiscount {

        void discount(AddCartLabModal.Detail detail);
    }


    public AddToCartAdapter(List<AddCartLabModal.Detail> list, Context context, TotalDiscount discount) {
        this.list = list;
        this.context = context;
        this.discount = discount;
    }

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new AddToCartAdapter.ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {

        holder.binding.medName.setText(list.get(position).getTitle());
        holder.binding.medPrice.setText("₹ "+String.valueOf(list.get(position).getTotalPrice())+"/-");
        holder.binding.labName.setText(list.get(position).getAboutTest());
        holder.binding.off.setText(list.get(position).getDiscount()+" %OFF");

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
