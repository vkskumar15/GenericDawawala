package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.UploadedPrescriptionsListAdapter;
import com.example.genericdawawalauser.databinding.FragmentUploadedPrescriptionsBinding;


public class UploadedPrescriptionsFragment extends Fragment {
    private FragmentUploadedPrescriptionsBinding fragmentUploadedPrescriptionsBinding = null;

    public UploadedPrescriptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUploadedPrescriptionsBinding = FragmentUploadedPrescriptionsBinding.inflate(inflater, container, false);
        setUploadedPrescriptionsRecyclerView();
        return fragmentUploadedPrescriptionsBinding.getRoot();
    }

    private void setUploadedPrescriptionsRecyclerView() {
        UploadedPrescriptionsListAdapter uploadedPrescriptionsListAdapter = new UploadedPrescriptionsListAdapter();
        fragmentUploadedPrescriptionsBinding.prescriptionsRecyclerview.setAdapter(uploadedPrescriptionsListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentUploadedPrescriptionsBinding = null;
    }
}