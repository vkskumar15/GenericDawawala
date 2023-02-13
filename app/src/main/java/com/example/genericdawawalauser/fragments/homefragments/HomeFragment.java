package com.example.genericdawawalauser.fragments.homefragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.DrAppointmentActivity;
import com.example.genericdawawalauser.activities.LabTestActivity;
import com.example.genericdawawalauser.activities.OnlineConsultationActivity;
import com.example.genericdawawalauser.activities.WalletActivity;
import com.example.genericdawawalauser.adapters.ImageSliderAdapter;
import com.example.genericdawawalauser.adapters.MedicinesAdapter;
import com.example.genericdawawalauser.adapters.PharmacyListAdapter;
import com.example.genericdawawalauser.adapters.SliderAdapter;
import com.example.genericdawawalauser.databinding.FragmentHomeBinding;
import com.example.genericdawawalauser.modalClass.SliderData;
import com.example.genericdawawalauser.pojo.SliderPojo;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding fragmentHomeBinding = null;
    String url1 = "https://firebasestorage.googleapis.com/v0/b/dovio-1c283.appspot.com/o/590-banner.jpg?alt=media&token=8243b376-d9bb-47d7-80f1-42d6562a78ff";
    String url2 = "https://firebasestorage.googleapis.com/v0/b/dovio-1c283.appspot.com/o/doctor-online-on-your-laptop-online-medicine-consultation-and-diagnosis-concept-web-banner-for-medical-app-ask-doctor-online-help-and-support-2CXD1PA.jpeg?alt=media&token=2454d675-b6e2-46fc-aa85-aabd547f9a15";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/dovio-1c283.appspot.com/o/gynecologist-online-service-platform-human-anatomy-ovary-gynecologist-online-service-platform-human-anatomy-ovary-womb-197052954.jpeg?alt=media&token=f1e2b530-fcc7-44c5-912a-615ccf50d215";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);

        sliderImage();

        onCLicks();
        setRecyclerViews();


        return fragmentHomeBinding.getRoot();
    }


    private void onCLicks() {
        fragmentHomeBinding.welcome.setText("Hii, " + App.getSharedPre().getString(AppConstants.USER_NAME));
        fragmentHomeBinding.profile.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.profileFragment);
        });
        fragmentHomeBinding.onlineCunsult.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), OnlineConsultationActivity.class));

        });
        fragmentHomeBinding.wallet.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), WalletActivity.class));

        });

        fragmentHomeBinding.drAppointment.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), DrAppointmentActivity.class));

        });


        fragmentHomeBinding.labTest.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), LabTestActivity.class));

        });
    }


    private void sliderImage() {
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList.add(new SliderData(this.url1));
        sliderDataArrayList.add(new SliderData(this.url2));
        sliderDataArrayList.add(new SliderData(this.url3));
        SliderAdapter adapter = new SliderAdapter(requireContext(), sliderDataArrayList);
        fragmentHomeBinding.slider.setAutoCycleDirection(0);
        fragmentHomeBinding.slider.setSliderAdapter(adapter);
        fragmentHomeBinding.slider.setScrollTimeInSec(4);
        fragmentHomeBinding.slider.setAutoCycle(true);
        fragmentHomeBinding.slider.startAutoCycle();
    }

    private void setRecyclerViews() {
        fragmentHomeBinding.flashSaleRecyclerview.setAdapter(new MedicinesAdapter());

        fragmentHomeBinding.trendingRecyclerview.setAdapter(new MedicinesAdapter());


        fragmentHomeBinding.justLaunchedRecyclerview.setAdapter(new MedicinesAdapter());

        fragmentHomeBinding.lightningDealsRecyclerview.setAdapter(new MedicinesAdapter());

        fragmentHomeBinding.trendingTodayRecyclerview.setAdapter(new MedicinesAdapter());
    }
}