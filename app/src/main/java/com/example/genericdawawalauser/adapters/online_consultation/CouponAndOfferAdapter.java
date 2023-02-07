package com.example.genericdawawalauser.adapters.online_consultation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.CouponAndOfferLayoutBinding;
import com.example.genericdawawalauser.databinding.ListPharmacyBinding;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentCouponModal;

import java.util.List;

public class CouponAndOfferAdapter extends RecyclerView.Adapter<CouponAndOfferAdapter.PharmacyListViewHolder> {

    Context context;
    private List<OnlineAppointmentCouponModal.Detail> list;
    OnItemClickCallback callback;

    public CouponAndOfferAdapter(Context context, List<OnlineAppointmentCouponModal.Detail> list, OnItemClickCallback callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PharmacyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PharmacyListViewHolder(CouponAndOfferLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyListViewHolder holder, int position) {

        holder.binding.couponName.setText(list.get(position).getCouponName());
        holder.binding.minOrderAmount.setText("Coupon Code valued at "+list.get(position).getMinOrderAmount()+" ₹"+ " or " +list.get(position).getPercentage()+ "% at Generic Dawawala");
        // holder.percentage.setText(list.get(position).getPercentage());
        holder.binding.expireDate.setText(list.get(position).getExpire());

        holder.binding.apply.setOnClickListener(v -> {

            callback.onItemClick(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PharmacyListViewHolder extends RecyclerView.ViewHolder {
        private CouponAndOfferLayoutBinding binding;

        public PharmacyListViewHolder(@NonNull CouponAndOfferLayoutBinding listPharmacyBinding) {
            super(listPharmacyBinding.getRoot());
            binding = listPharmacyBinding;
        }

    }

    public interface OnItemClickCallback {
        void onItemClick(OnlineAppointmentCouponModal.Detail detail);
    }

}
