package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentRadiologyDetailsBinding;

public class RadiologyDetailsFragment extends Fragment {

    FragmentRadiologyDetailsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     binding = FragmentRadiologyDetailsBinding.inflate(inflater, container, false);

     return binding.getRoot();
    }
}