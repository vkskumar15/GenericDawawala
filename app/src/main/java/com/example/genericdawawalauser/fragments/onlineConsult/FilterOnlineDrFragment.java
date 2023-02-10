package com.example.genericdawawalauser.fragments.onlineConsult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentFilterOnlineDrBinding;

public class FilterOnlineDrFragment extends Fragment {
    FragmentFilterOnlineDrBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentFilterOnlineDrBinding.inflate(inflater, container, false);

       onClicks();
       return binding.getRoot();

    }

    private void onClicks() {
        binding.back.setOnClickListener(v -> {

            requireActivity().onBackPressed();
        });
    }
}