package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.ListPharmacyBinding;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyListAdapter.PharmacyListViewHolder> {

    Context context ;
    OnItemClickCallback callback ;

    public PharmacyListAdapter(Context context, OnItemClickCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PharmacyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PharmacyListViewHolder(ListPharmacyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyListViewHolder holder, int position) {
        holder.binding.ivItemImage.setImageResource(R.drawable.ic_traditional_medicine);
        holder.binding.tvText.setText("Ayurvedic");

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class PharmacyListViewHolder extends RecyclerView.ViewHolder {
        private ListPharmacyBinding binding;

        public PharmacyListViewHolder(@NonNull ListPharmacyBinding listPharmacyBinding) {
            super(listPharmacyBinding.getRoot());
            binding = listPharmacyBinding;
        }


        public void bind(int position) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    callback.onItemClick(position);
                }
            });

        }
    }
    public interface OnItemClickCallback {
        void onItemClick(int position);
    }

}
