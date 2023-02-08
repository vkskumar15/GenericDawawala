package com.example.genericdawawalauser.fragments.drAppointment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentLocationBinding;


public class LocationFragment extends Fragment {
    FragmentLocationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);

        onClick();

        return binding.getRoot();
    }

    private void onClick() {
        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });
    }
}