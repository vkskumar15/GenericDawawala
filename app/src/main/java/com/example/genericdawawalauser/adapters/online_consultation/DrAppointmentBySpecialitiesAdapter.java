package com.example.genericdawawalauser.adapters.online_consultation;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.DoctorAppointmentListBinding;
import com.example.genericdawawalauser.databinding.DoctorListBinding;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;

import java.util.ArrayList;
import java.util.List;

public class DrAppointmentBySpecialitiesAdapter extends RecyclerView.Adapter<DrAppointmentBySpecialitiesAdapter.MyViewHolder> implements Filterable {
    Context context;
    OnLineFee onLineFee;
    private List<DoctorModelDetails> list;
    OffLineFee offLineFee;
    private List<DoctorModelDetails> filteredList;
    public static List<DoctorModelDetails> unFilteredList;
    OnClicks onClickInterFace;

    public interface OnLineFee {
        void onLineFee(DoctorModelDetails doctorModelDetails);
    }

    public interface OnClicks {
        void onItemClick(DoctorModelDetails doctorModelDetails);
    }

    public interface OffLineFee {
        void offLineFee(DoctorModelDetails doctorModelDetails);
    }

    public DrAppointmentBySpecialitiesAdapter(Context context, OnLineFee onLineFee, List<DoctorModelDetails> list, OffLineFee offLineFee, OnClicks onClickInterFace) {
        this.context = context;
        this.onLineFee = onLineFee;
        this.list = list;
        this.filteredList = list;
        this.offLineFee = offLineFee;
        this.onClickInterFace = onClickInterFace;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(DoctorAppointmentListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(Uri.parse(list.get(position).getDoctorImage())).error(R.drawable.doctor_12).into(holder.binding.ivDoctorImage);
        holder.binding.tvDoctorName.setText(list.get(position).getName());
        holder.binding.qualificationTv1.setText(list.get(position).getQualificationTitle());
        holder.binding.specialityTv1.setText(list.get(position).getSpecialistTitle());
        holder.binding.price.setText("Online Fees: ₹ " + list.get(position).getOnline_price());
        holder.binding.workInTv.setText(list.get(position).getClinic_Name());
        holder.binding.addressTv.setText(list.get(position).getAddress());
        holder.binding.distance.setText("Distance: " + list.get(position).getDistance()+" Km");
        // holder.binding.ratingBar.setRating(Float.valueOf(list.get(position).getRating()));
        String experience = list.get(position).getWorkExp();
        holder.binding.experienceYearsTextView.setText(experience + " +");

        holder.binding.onlineFee.setText("Online Fees: ₹ " + list.get(position).getOnline_price());

        holder.binding.offlineFee.setText("Offline Fees: ₹ " + list.get(position).getOffline_price());

        holder.binding.moreAboutDoctorTextView.setOnClickListener(v -> {
            onClickInterFace.onItemClick(list.get(position));
        });

        holder.binding.offlineFee.setOnClickListener(v -> {
            offLineFee.offLineFee(list.get(position));
        });

        holder.binding.onlineFee.setOnClickListener(v -> {
            onLineFee.onLineFee(list.get(position));
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<DoctorModelDetails> filter = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filter.addAll(DrAppointmentBySpecialitiesAdapter.unFilteredList);
                } else {
                    String value = constraint.toString().toLowerCase().trim();
                    for (DoctorModelDetails doctorModelDetails : filteredList) {
                        if (doctorModelDetails.getName().toLowerCase().contains(value)) {
                            filter.add(doctorModelDetails);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list.clear();
                // list.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        DoctorAppointmentListBinding binding;

        public MyViewHolder(DoctorAppointmentListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }


    }
}
