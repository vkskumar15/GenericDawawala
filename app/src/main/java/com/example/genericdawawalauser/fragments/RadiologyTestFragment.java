package com.example.genericdawawalauser.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPackageAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.RadiologyCategoryAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadiologyTestBinding;

public class RadiologyTestFragment extends Fragment {
    FragmentRadiologyTestBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     binding = FragmentRadiologyTestBinding.inflate(inflater, container, false);

     setCategoryAdapter();
     setPackageAdapter();
     return binding.getRoot();

    }

    private void setCategoryAdapter() {

        RadiologyCategoryAdapter adapter = new RadiologyCategoryAdapter();
        binding.categoryNameRecyclerview.setAdapter(adapter);
    }

    private void setPackageAdapter() {

        LabPackageAdapter adapter = new LabPackageAdapter();
        binding.packageNameRecyclerview.setAdapter(adapter);
    }
}