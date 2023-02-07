package com.example.genericdawawalauser.fragments.onlineConsult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentDoctorDetailsBinding;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;


public class DoctorDetailsFragment extends Fragment {

    FragmentDoctorDetailsBinding binding;
    public static DoctorModelDetails doctorModelDetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDoctorDetailsBinding.inflate(inflater, container, false);

        setData();
        return binding.getRoot();

    }

    private void setData() {

        binding.imageView.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        binding.doctorName.setText(doctorModelDetails.getName());
        binding.textView3.setText(doctorModelDetails.getName());
        binding.textView4.setText(doctorModelDetails.getQualificationTitle());
        String experience = doctorModelDetails.getWorkExp();
        binding.textView6.setText(experience + " +");
        binding.textView8.setText(doctorModelDetails.getLanguage());
        binding.textView12.setText(doctorModelDetails.getClinic_Name());
        binding.textView13.setText(doctorModelDetails.getAddress());
        binding.textView22.setText(doctorModelDetails.getQualificationTitle());
        binding.textView42.setText(doctorModelDetails.getSpecialistTitle());
        binding.textView42.setText(doctorModelDetails.getSpecialistTitle());
        Glide.with(requireActivity()).load(doctorModelDetails.getDoctorImage()).into(binding.ivDoctorImage);
    }
}