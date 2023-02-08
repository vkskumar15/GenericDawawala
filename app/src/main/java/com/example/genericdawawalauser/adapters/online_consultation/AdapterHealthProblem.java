package com.example.genericdawawalauser.adapters.online_consultation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.HealthProblemModal;

import java.util.ArrayList;
import java.util.List;

public class AdapterHealthProblem extends RecyclerView.Adapter<AdapterHealthProblem.ViewHolder> implements Filterable {

    Context context;
    Select select;
    List<HealthProblemModal.Detail> list;
    private List<HealthProblemModal.Detail> filteredList;
    public static List<HealthProblemModal.Detail> unFilteredList;

    public AdapterHealthProblem(Context context, List<HealthProblemModal.Detail> list, Select select) {

        this.context = context;
        this.select = select;
        this.filteredList = list;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialities_layout, parent, false);
        return new ViewHolder(view);
    }

    public void loadData(List<HealthProblemModal.Detail> list) {

        this.list = list;
        this.filteredList = list;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textView.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select.onClick(list.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {

        return (list != null && list.size() != 0 ? list.size() : 0);

    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<HealthProblemModal.Detail> filter = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {

                    filter.addAll(AdapterHealthProblem.unFilteredList);

//                    Log.d(TAG, "performFiltering: list size : "+FileManagerHealthLockerAdapter.unFilteredList.size());


                } else {

                    String value = constraint.toString().toLowerCase().trim();

                    for (HealthProblemModal.Detail doctorModelDetails : filteredList) {

                        if (doctorModelDetails.getTitle().toLowerCase().contains(value)) {

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
                // list.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profile_image);
            textView = itemView.findViewById(R.id.specialityTv);
            relativeLayout = itemView.findViewById(R.id.relative_location);

        }
    }

    public interface Select {

        public void onClick(HealthProblemModal.Detail doctorModelDetails);

    }

}
