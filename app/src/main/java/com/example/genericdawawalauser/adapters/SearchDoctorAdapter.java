package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.CommonSearchItemsBinding;


public class SearchDoctorAdapter extends RecyclerView.Adapter<SearchDoctorAdapter.MyViewHolder> {

    Context context ;
    OnItemClickCallBack callback ;

    public SearchDoctorAdapter(Context context, OnItemClickCallBack callback) {
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SearchDoctorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchDoctorAdapter.MyViewHolder(CommonSearchItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDoctorAdapter.MyViewHolder holder, int position) {
     holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CommonSearchItemsBinding binding ;
        public MyViewHolder(@NonNull CommonSearchItemsBinding itemView) {
            super(itemView.getRoot());


            binding =itemView ;
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

    public  interface OnItemClickCallBack
    {
        void onItemClick(int pos);
    }
}
