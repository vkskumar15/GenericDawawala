package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.CreditMoneyLayoutBinding;
import com.example.genericdawawalauser.modalClass.WalletHistoryModal;

import java.util.List;

public class MyWalletAdapter extends RecyclerView.Adapter<MyWalletAdapter.ViewHolder> {
    private Context context;
    private List<WalletHistoryModal.Details> list;


    public MyWalletAdapter(Context context, List<WalletHistoryModal.Details> list) {
        this.context = context;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(CreditMoneyLayoutBinding.inflate(LayoutInflater.from(this.context), parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.binding.txtAmount.setText(list.get(position).getWalletAmount());
        holder.binding.txtTransactionIdLab.setText(list.get(position).getWalletAmount());
        holder.binding.txtPaymentIdLab.setText(list.get(position).getRazorpayPaymentId());
        holder.binding.txtDateAndTime.setText(list.get(position).getCreated());

        if (list.get(position).getType().equals("credit"))
        {
            holder.binding.credited.setText("Credited");


        }else if (list.get(position).getType().equals("debit"))
        {
            holder.binding.credited.setText("Debited");
            holder.binding.credit.setImageResource(R.drawable.debit);
        }
    }

    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CreditMoneyLayoutBinding binding;

        public ViewHolder(CreditMoneyLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
