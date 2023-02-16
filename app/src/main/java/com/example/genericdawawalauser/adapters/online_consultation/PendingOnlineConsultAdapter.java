package com.example.genericdawawalauser.adapters.online_consultation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.databinding.PendingOnlineAppointmetLayoutBinding;
import com.example.genericdawawalauser.databinding.SpecialitiesLayoutBinding;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;

import java.util.List;

public class PendingOnlineConsultAdapter extends RecyclerView.Adapter<PendingOnlineConsultAdapter.ViewHolder> {

    List<PendingOnlineAppointmentModal.Detail> list;
    private Context context;
    private Reschedule reschedule;
    private DownLoad downLoad;
    private ViewPrescription viewPrescription;


    public interface Reschedule {

        void reschedule(PendingOnlineAppointmentModal.Detail detail);
        void cancel(PendingOnlineAppointmentModal.Detail detail, int pos);
    }
     public interface DownLoad {

        void downLoad(PendingOnlineAppointmentModal.Detail detail);
    }

     public interface ViewPrescription {

        void viewPrescription(PendingOnlineAppointmentModal.Detail detail);
    }


    public PendingOnlineConsultAdapter(List<PendingOnlineAppointmentModal.Detail> list, Context context, Reschedule reschedule, DownLoad downLoad, ViewPrescription viewPrescription) {
        this.list = list;
        this.context = context;
        this.reschedule = reschedule;
        this.downLoad = downLoad;
        this.viewPrescription = viewPrescription;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(PendingOnlineAppointmetLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getDoctorImage()).into(holder.binding.circularImage);
        holder.binding.drName.setText("Dr. " + list.get(position).getDocName());
        holder.binding.txtTime.setText(list.get(position).getAppointmentDate());
        holder.binding.txtLanguage.setText(list.get(position).getLanguage());
        holder.binding.txtPatientName.setText(list.get(position).getPatientName());
        holder.binding.txtPaymentIdLab.setText(list.get(position).getTransctionId());
        holder.binding.txtDateAndTime.setText(list.get(position).getHealthProblem());
        holder.binding.specialityTv.setText(list.get(position).getSpecialty());
        holder.binding.txtAmount.setText("₹ " + list.get(position).getAmount());

        if (list.get(position).getAppointmentType().equalsIgnoreCase("2")) {
            holder.binding.onOffStatus.setText("Online Appointment");
            holder.binding.onOffStatus.setVisibility(View.VISIBLE);
        }

        else if (list.get(position).getAppointmentType().equalsIgnoreCase("3")) {
            holder.binding.onOffStatus.setText("Offline Appointment");
            holder.binding.onOffStatus.setVisibility(View.VISIBLE);
        }

        if (list.get(position).getStatus().equalsIgnoreCase("1")) {
            holder.binding.start.setVisibility(View.GONE);
            holder.binding.status.setText("Status: Approved");
            holder.binding.status.setTextColor(Color.parseColor("#0daaed"));

            if (list.get(position).getAppointmentType().equalsIgnoreCase("3"))
            {
                holder.binding.address.setVisibility(View.VISIBLE);
                holder.binding.address.setText(list.get(position).getAddress());
            }
        }

        if (list.get(position).getStatus().equalsIgnoreCase("3")) {
            holder.binding.start.setVisibility(View.GONE);
            holder.binding.startTwo.setVisibility(View.VISIBLE);
            holder.binding.status.setText(list.get(position).getCancelBy());
            holder.binding.status.setTextColor(Color.parseColor("#c91b20"));
            holder.binding.status.setText("Appointment Completed");

        }

        if (list.get(position).getCancelBy().equalsIgnoreCase("cancelled by doctor")) {
            holder.binding.startTwo.setVisibility(View.GONE);
            holder.binding.refund.setVisibility(View.VISIBLE);
            holder.binding.start.setVisibility(View.GONE);

            holder.binding.refund.setText("Note: Your full amount has been refunded check your wallet");
        }

        if (list.get(position).getCancelBy().equalsIgnoreCase("doctor")) {
            holder.binding.startTwo.setVisibility(View.GONE);
            holder.binding.refund.setVisibility(View.VISIBLE);
            holder.binding.start.setVisibility(View.GONE);

            holder.binding.refund.setText("Note: Your full amount has been refunded check your wallet");
        }

        if (list.get(position).getCancelBy().equalsIgnoreCase("cancelled by user")) {
            holder.binding.startTwo.setVisibility(View.GONE);
            holder.binding.refund.setVisibility(View.VISIBLE);
            holder.binding.start.setVisibility(View.GONE);
            holder.binding.status.setText(list.get(position).getCancelBy());
            holder.binding.refund.setText("Note: Your 80%  amount has been refunded check your wallet");
        }


        holder.binding.rescheduleButton.setOnClickListener(v -> {

            reschedule.reschedule(list.get(position));
        });

        holder.binding.cancelButton.setOnClickListener(v -> {

            reschedule.cancel(list.get(position), position);
            notifyDataSetChanged();
        });

        holder.binding.viewButton.setOnClickListener(v -> {

            viewPrescription.viewPrescription(list.get(position));
        });

        holder.binding.downloadButton.setOnClickListener(v -> {

            downLoad.downLoad(list.get(position));
            notifyDataSetChanged();
        });
    }

    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PendingOnlineAppointmetLayoutBinding binding;

        public ViewHolder(PendingOnlineAppointmetLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
