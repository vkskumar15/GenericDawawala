package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentAddPackageCartBinding;


public class AddPackageCartFragment extends Fragment {

    FragmentAddPackageCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    binding = FragmentAddPackageCartBinding.inflate(inflater, container, false);

    return binding.getRoot();


    }
}