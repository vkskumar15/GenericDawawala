package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.RadiologyTestDetailsAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadiologyDetailsBinding;
import com.example.genericdawawalauser.modalClass.AddToCartPackageModal;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

public class RadiologyDetailsFragment extends Fragment {
    FragmentRadiologyDetailsBinding binding;
    public static RadiologyPackageTestModal.Detail detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRadiologyDetailsBinding.inflate(inflater, container, false);

        onClicks();
        setAdapter();

        return binding.getRoot();
    }

    private void setAdapter() {
        binding.medicineName.setText("Package Name: " + detail.getTitle());
        binding.unisex.setText(detail.getGender());
        binding.ageGroup.setText(detail.getAge());
        binding.totalTest.setText("Total Tests Included: " + String.valueOf(detail.getTotalTests()));

        if (detail.getPrescription().equalsIgnoreCase("0")) {

            binding.preparation.setText("Preparation Required");

        } else if (detail.getPrescription().equalsIgnoreCase("1")) {

            binding.preparation.setText("Preparation Not Required");
        }


        RadiologyTestDetailsAdapter adapter = new RadiologyTestDetailsAdapter(detail.getTests(), requireContext());
        binding.categoryNameRecyclerview.setAdapter(adapter);
    }

    private void onClicks() {
        binding.back.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });

    }
}