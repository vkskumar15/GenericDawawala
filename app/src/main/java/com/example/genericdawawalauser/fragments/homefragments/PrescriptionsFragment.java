package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentPrescriptionsBinding;

public class PrescriptionsFragment extends Fragment {

    private FragmentPrescriptionsBinding fragmentPrescriptionsBinding = null;

    public PrescriptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPrescriptionsBinding = FragmentPrescriptionsBinding.inflate(inflater, container, false);
        setClicksOnViews();
        return fragmentPrescriptionsBinding.getRoot();
    }

    private void setClicksOnViews() {
        fragmentPrescriptionsBinding.uploadedPrescriptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_prescriptionsFragment_to_uploadedPrescriptionsFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPrescriptionsBinding = null;
    }
}