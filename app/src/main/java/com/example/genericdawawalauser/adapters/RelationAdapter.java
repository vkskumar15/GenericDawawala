package com.example.genericdawawalauser.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.PatientLayoutBinding;
import com.example.genericdawawalauser.modalClass.RelationModal;
import java.util.List;

public class RelationAdapter extends RecyclerView.Adapter<RelationAdapter.ViewHolder> {
    private Context context;
    private List<RelationModal> list;
    private SelectData selectData;
    private int rowIndex=-1;

    public interface SelectData{

        void onClick(RelationModal relationModal);

    }
    public RelationAdapter(Context context2, List<RelationModal> list2) {
        this.context = context2;
        this.list = list2;
    }


    public RelationAdapter(Context context, List<RelationModal> list, SelectData selectData) {
        this.context = context;
        this.list = list;
        this.selectData = selectData;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(PatientLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.testName.setText(this.list.get(position).getName());

        holder.itemView.setOnClickListener(v -> {

            rowIndex = position;

            selectData.onClick(list.get(position));

            notifyDataSetChanged();


        });

        if (rowIndex == position) {

            holder.itemView.setBackgroundResource(R.drawable.outline_select_slot_address);
            holder.binding.testName.setTextColor(Color.WHITE);

        } else {

            holder.binding.testName.setTextColor(Color.BLACK);

        }
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
