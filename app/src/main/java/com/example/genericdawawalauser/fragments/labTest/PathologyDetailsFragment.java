package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentPathologyDetailsBinding;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;

public class PathologyDetailsFragment extends Fragment {
    FragmentPathologyDetailsBinding binding;
    public static MedicineDataModal.Detail detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPathologyDetailsBinding.inflate(inflater, container, false);

        onClicks();
        setData();

        return binding.getRoot();

    }

    private void setData() {
        binding.medName.setText(detail.getTitle());
        binding.aboutTest.setText(detail.getAboutTest());
        binding.ageGroup.setText(detail.getAge()+" age group");
        binding.medPrice.setText("₹ " + detail.getPrice() + "/-");

        if (detail.getHomeCollectionCheck().equalsIgnoreCase("0")) {
            binding.sample.setText("Center In Visit");
        } else {
            binding.sample.setText("Home Sample Collection");

        }

        if (detail.getPrescriptionCheck().equalsIgnoreCase("0")) {
            binding.preparation.setText("Preparation Required");
        } else {
            binding.preparation.setText("No Preparation");

        }


    }

    private void onClicks() {

        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });
    }
}