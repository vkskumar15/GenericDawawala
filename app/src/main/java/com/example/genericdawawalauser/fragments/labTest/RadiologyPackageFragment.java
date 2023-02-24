package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentRadiologyPackageBinding;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;

import java.util.ArrayList;

public class RadiologyPackageFragment extends Fragment {
    FragmentRadiologyPackageBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    binding = FragmentRadiologyPackageBinding.inflate(inflater, container, false);



    return binding.getRoot();

    }

}