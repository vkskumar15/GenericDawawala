package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.TestLayoutBinding;
import com.example.genericdawawalauser.modalClass.GetUserLabModal;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;

import java.util.List;

public class LabTestPackageAdapter extends RecyclerView.Adapter<LabTestPackageAdapter.ViewHolder> {
    List<GetUserLabModal.Detail.Test> list;
    private Context context;

    public LabTestPackageAdapter(List<GetUserLabModal.Detail.Test> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LabTestPackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabTestPackageAdapter.ViewHolder(TestLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabTestPackageAdapter.ViewHolder holder, int position) {

        holder.binding.testName.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TestLayoutBinding binding;

        public ViewHolder(@NonNull TestLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
