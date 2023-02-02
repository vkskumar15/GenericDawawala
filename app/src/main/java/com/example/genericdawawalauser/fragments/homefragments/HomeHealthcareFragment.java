package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.AdapterHomeHealthCare;
import com.example.genericdawawalauser.databinding.FragmentHomeHealthcareBinding;


public class HomeHealthcareFragment extends Fragment {
    FragmentHomeHealthcareBinding binding;

    public HomeHealthcareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        setAdapter();


        return binding.getRoot();
    }

    private void setAdapter() {

        binding.rvHomeHealthcare.setAdapter(new AdapterHomeHealthCare());
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }
}