package com.example.genericdawawalauser.adapters.online_consultation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.databinding.PendingOnlineAppointmetLayoutBinding;
import com.example.genericdawawalauser.modalClass.PendingOnlineAppointmentModal;

import java.util.List;

public class ApprovedOnlineConsultAdapter extends RecyclerView.Adapter<ApprovedOnlineConsultAdapter.ViewHolder> {

    List<PendingOnlineAppointmentModal.Detail> list;
    private Context context;
    private Reschedule reschedule;


    public interface Reschedule
    {
        void reschedule(PendingOnlineAppointmentModal.Detail detail);

        void cancel(PendingOnlineAppointmentModal.Detail detail);
    }

    public ApprovedOnlineConsultAdapter(List<PendingOnlineAppointmentModal.Detail> list, Context context, Reschedule reschedule) {
        this.list = list;
        this.context = context;
        this.reschedule = reschedule;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(PendingOnlineAppointmetLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getDoctorImage()).into(holder.binding.circularImage);
        holder.binding.drName.setText("Dr. "+list.get(position).getDocName());
        holder.binding.txtTime.setText(list.get(position).getAppointmentDate());
        holder.binding.txtLanguage.setText(list.get(position).getLanguage());
        holder.binding.txtPatientName.setText(list.get(position).getPatientName());
        holder.binding.txtPaymentIdLab.setText(list.get(position).getTransctionId());
        holder.binding.txtDateAndTime.setText(list.get(position).getHealthProblem());
        holder.binding.txtAmount.setText("₹ "+list.get(position).getAmount());

        holder.binding.rescheduleButton.setOnClickListener(v -> {

            reschedule.reschedule(list.get(position));
        });

        holder.binding.cancelButton.setOnClickListener(v -> {

            reschedule.cancel(list.get(position));
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
