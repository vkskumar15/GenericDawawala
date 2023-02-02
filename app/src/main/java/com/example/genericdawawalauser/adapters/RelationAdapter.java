package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.modalClass.RelationModal;
import java.util.List;

public class RelationAdapter extends RecyclerView.Adapter<RelationAdapter.ViewHolder> {
    private Context context;
    private List<RelationModal> list;

    public RelationAdapter(Context context2, List<RelationModal> list2) {
        this.context = context2;
        this.list = list2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(PatientLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.testName.setText(this.list.get(position).getName());
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PatientLayoutBinding binding;

        public ViewHolder(PatientLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
