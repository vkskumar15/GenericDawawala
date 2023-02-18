package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentLabCouponCodeBinding;


public class LabCouponCodeFragment extends Fragment {
    FragmentLabCouponCodeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentLabCouponCodeBinding.inflate(inflater, container, false);

       binding.back.setOnClickListener(v -> {

           requireActivity().onBackPressed();

       });


       return binding.getRoot();
    }
}