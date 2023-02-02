package com.example.genericdawawalauser.adapters.online_consultation;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.databinding.SpecialitiesLayoutBinding;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.ViewHolder> {

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(SpecialitiesLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SpecialitiesLayoutBinding binding;

        public ViewHolder(SpecialitiesLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
