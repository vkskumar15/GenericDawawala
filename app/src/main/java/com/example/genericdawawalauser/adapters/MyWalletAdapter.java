package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.CreditMoneyLayoutBinding;

public class MyWalletAdapter extends RecyclerView.Adapter<MyWalletAdapter.ViewHolder> {
    private Context context;

    public MyWalletAdapter(Context context2) {
        this.context = context2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(CreditMoneyLayoutBinding.inflate(LayoutInflater.from(this.context), parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CreditMoneyLayoutBinding binding;

        public ViewHolder(CreditMoneyLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
