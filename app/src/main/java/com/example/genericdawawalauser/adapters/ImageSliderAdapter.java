package com.example.genericdawawalauser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.SlideBinding;
import com.example.genericdawawalauser.pojo.SliderPojo;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.SliderViewHolder> {

    private Context context;
    private List<SliderPojo.Detail> imgArray = new ArrayList<>();
    private ViewPager2 viewPager;


    public ImageSliderAdapter(Context context, List<SliderPojo.Detail> imgArray, ViewPager2 viewPager) {
        this.imgArray = imgArray;
        this.viewPager = viewPager;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(SlideBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Glide.with(context).load(imgArray.get(position).getSliderImage()).placeholder(R.drawable.doctorgroup).into(holder.binding.image);
        if (position == imgArray.size() - 2) {
            viewPager.post(sliderRunnable);
        }
    }

    @Override
    public int getItemCount() {
        return imgArray.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        SlideBinding binding;

        public SliderViewHolder(@NonNull SlideBinding slideBinding) {
            super(slideBinding.getRoot());
            binding = slideBinding;
        }
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            imgArray.addAll(imgArray);
            notifyDataSetChanged();
        }
    };

}
