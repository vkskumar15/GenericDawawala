package com.example.genericdawawalauser.adapters.online_consultation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.EveningSlotsModel;

import java.util.ArrayList;
import java.util.List;

public class GridViewSelectEveningSlotAdapter extends BaseAdapter {
    private Context context;
    private List<EveningSlotsModel> timeList;
    private LayoutInflater inflater;
    private int rowIndex=-1;

    List<String> slot_list=new ArrayList<>();

    Select select;

    public GridViewSelectEveningSlotAdapter(Context context, List<EveningSlotsModel> timeList, Select select) {
        this.context = context;
        this.timeList = timeList;
        this.select = select;
    }


    @Override
    public int getCount() {
        return timeList.size()-1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)

            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.layout_grid_time_select_slot_and_address_doctor, null);

        }

        TextView textView = convertView.findViewById(R.id.txt_times);

        getSlotsList(timeList);

        textView.setText(slot_list.get(position));

        textView.setOnClickListener(v -> {

            rowIndex = position;

            select.onClick(slot_list.get(position));

            notifyDataSetChanged();


        });

        if (rowIndex == position) {

            textView.setBackgroundResource(R.drawable.outline_select_slot_address);
            textView.setTextColor(Color.WHITE);

        } else {

            textView.setBackgroundResource(R.drawable.outline_select_slot_address_unselected);
            textView.setTextColor(Color.BLACK);

        }

        return convertView;
    }

    public interface Select{

        void onClick(String slot);

    }

    private void getSlotsList(List< EveningSlotsModel > list) {

        String startTime = list.get(0).getSlot_time()+" PM";

        StringBuilder sb = new StringBuilder();


        for (int i = 1; i < list.size(); i++) {

            sb.append(startTime).append(" - ").append(list.get(i).getSlot_time()).append(" PM");

            slot_list.add(sb.toString());

            sb.setLength(0);

            startTime = list.get(i).getSlot_time()+" PM";

        }

    }

}
