package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.RadiologyLabTestAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadioLogyLabTestBinding;

public class RadioLogyLabTestFragment extends Fragment {
    FragmentRadioLogyLabTestBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadioLogyLabTestBinding.inflate(inflater, container, false);

        RadiologyLabTestAdapter adapter = new RadiologyLabTestAdapter();
        binding.packageNameRecyclerview.setAdapter(adapter);
        return binding.getRoot();

    }
}