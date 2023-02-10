package com.example.genericdawawalauser.adapters.online_consultation;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.DoctorListBinding;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;

import java.util.ArrayList;
import java.util.List;

public class DoctorBySpecialitiesAdapter extends RecyclerView.Adapter<DoctorBySpecialitiesAdapter.MyViewHolder> implements Filterable {
    Context context;
    OnClick onClickInterFace;
    private List<DoctorModelDetails> list;
    BookNow bookNow;
    private List<DoctorModelDetails> filteredList;
    public static List<DoctorModelDetails> unFilteredList;


    public interface OnClick {
        void onItemClick(DoctorModelDetails doctorModelDetails);
    }

    public interface BookNow {
        void bookNow(DoctorModelDetails doctorModelDetails);
    }

    public DoctorBySpecialitiesAdapter(Context context, OnClick onClickInterFace, List<DoctorModelDetails> list, BookNow bookNow) {
        this.context = context;
        this.onClickInterFace = onClickInterFace;
        this.list = list;
        this.filteredList = list;
        this.bookNow = bookNow;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(DoctorListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(Uri.parse(list.get(position).getDoctorImage())).error(R.drawable.doctor_12).into(holder.binding.ivDoctorImage);
        holder.binding.tvDoctorName.setText(list.get(position).getName());
        holder.binding.qualificationTv1.setText(list.get(position).getQualificationTitle());
        holder.binding.specialityTv1.setText(list.get(position).getSpecialistTitle());
        holder.binding.price.setText("Online Fees: ₹ " + list.get(position).getOnline_price());
        holder.binding.workInTv.setText(list.get(position).getClinic_Name());
        holder.binding.addressTv.setText(list.get(position).getAddress());
        holder.binding.language.setText("Language "+list.get(position).getLanguage());
        // holder.binding.ratingBar.setRating(Float.valueOf(list.get(position).getRating()));
        String experience = list.get(position).getWorkExp();
        holder.binding.experienceYearsTextView.setText(experience + " +");

        holder.binding.moreAboutDoctorTextView.setOnClickListener(v -> {
            onClickInterFace.onItemClick(list.get(position));
        });

        holder.binding.btBooknow.setOnClickListener(v -> {

            bookNow.bookNow(list.get(position));
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<DoctorModelDetails> filter = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filter.addAll(DoctorBySpecialitiesAdapter.unFilteredList);
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
                list.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        DoctorListBinding binding;

        public MyViewHolder(DoctorListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }


    }
}
