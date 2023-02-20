package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.AddPatientsAdapter;
import com.example.genericdawawalauser.databinding.FragmentGetPatitentBinding;


public class GetPatientFragment extends Fragment {
    FragmentGetPatitentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentGetPatitentBinding.inflate(inflater, container, false);


       setAdapter();
       onClicks();

       return binding.getRoot();

    }

    private void onClicks() {
        binding.addPatient.setOnClickListener(view -> {

            Navigation.findNavController(view).navigate(R.id.addPatientFragment);
        });
    }

    private void setAdapter() {
//        AddPatientsAdapter addPatientsAdapter = new AddPatientsAdapter();
//        binding.recyclerviewCondition.setAdapter(addPatientsAdapter);
    }
}