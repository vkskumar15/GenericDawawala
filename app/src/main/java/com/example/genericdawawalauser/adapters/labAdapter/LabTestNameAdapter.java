package com.example.genericdawawalauser.adapters.labAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;
import java.util.List;

public class LabTestNameAdapter extends RecyclerView.Adapter<LabTestNameAdapter.ViewHolder> {

    List<LabDetailsModal.Detail.Test> list;
    private Context context;

    public LabTestNameAdapter(List<LabDetailsModal.Detail.Test> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public LabTestNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabTestNameAdapter.ViewHolder(PatientLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabTestNameAdapter.ViewHolder holder, int position) {

        holder.binding.testName.setText(list.get(position).getTestName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PatientLayoutBinding binding;

        public ViewHolder(@NonNull PatientLayoutBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }


    }
}
