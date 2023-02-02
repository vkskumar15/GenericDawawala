package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.modalClass.SliderData;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    private final List<SliderData> mSliderItems;

    public SliderAdapter(Context context, ArrayList<SliderData> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
    }

    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, (ViewGroup) null));
    }

    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        ((RequestBuilder) Glide.with(viewHolder.itemView).load(this.mSliderItems.get(position).getImgUrl()).fitCenter()).into(viewHolder.imageViewBackground);
    }

    public int getCount() {
        return this.mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageViewBackground;
        View itemView;

        public SliderAdapterViewHolder(View itemView2) {
            super(itemView2);
            this.imageViewBackground = (ImageView) itemView2.findViewById(R.id.myimage);
            this.itemView = itemView2;
        }
    }
}
