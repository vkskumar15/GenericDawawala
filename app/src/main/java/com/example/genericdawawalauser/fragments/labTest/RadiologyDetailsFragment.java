package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.RadiologyTestDetailsAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadiologyDetailsBinding;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;

public class RadiologyDetailsFragment extends Fragment {
    FragmentRadiologyDetailsBinding binding;
    public  static RadiologyPackageTestModal.Detail detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     binding = FragmentRadiologyDetailsBinding.inflate(inflater, container, false);

     onClicks();
     setAdapter();


     return binding.getRoot();
    }

    private void setAdapter() {
        binding.medicineName.setText("Package Name: "+detail.getTitle());
        binding.unisex.setText(detail.getGender());
        binding.ageGroup.setText(detail.getAge());
        binding.totalTest.setText("Total Tests Included: "+String.valueOf(detail.getTotalTests()));

        RadiologyTestDetailsAdapter adapter =  new RadiologyTestDetailsAdapter(detail.getTests(), requireContext());
        binding.categoryNameRecyclerview.setAdapter(adapter);
    }

    private void onClicks() {
        binding.back.setOnClickListener(view -> {

            requireActivity().onBackPressed();


        });
    }
}