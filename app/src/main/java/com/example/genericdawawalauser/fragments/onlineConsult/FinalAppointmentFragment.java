package com.example.genericdawawalauser.fragments.onlineConsult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentFinalAppointmentBinding;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;


public class FinalAppointmentFragment extends Fragment {

    FragmentFinalAppointmentBinding binding;
    public static DoctorModelDetails doctorModelDetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFinalAppointmentBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }
}