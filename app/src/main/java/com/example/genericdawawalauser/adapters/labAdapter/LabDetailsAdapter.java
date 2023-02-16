package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.databinding.LabDeatilsLayoutBinding;
import com.example.genericdawawalauser.databinding.LabPackageLayoutBinding;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;

import java.util.List;

public class LabDetailsAdapter extends RecyclerView.Adapter<LabDetailsAdapter.ViewHolder> {
    List<LabDetailsModal.Detail> list;
    Context context;
    SelectLab selectLab;

    public interface SelectLab{

        void selectLab(LabDetailsModal.Detail detail);
    }

    public LabDetailsAdapter(List<LabDetailsModal.Detail> list, Context context, SelectLab selectLab) {
        this.list = list;
        this.context = context;
        this.selectLab = selectLab;
    }

    @NonNull
    @Override
    public LabDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabDetailsAdapter.ViewHolder(LabDeatilsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabDetailsAdapter.ViewHolder holder, int position) {

        holder.binding.name.setText(list.get(position).getName());
        holder.binding.about.setText(list.get(position).getAbout());
        Glide.with(context).load(list.get(position).getImage()).into(holder.binding.profileImage);

        holder.binding.selectBtn.setOnClickListener(v -> {

            selectLab.selectLab(list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LabDeatilsLayoutBinding binding;

        public ViewHolder(@NonNull LabDeatilsLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }
}
