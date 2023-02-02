package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.WellnessPackagesListAdapter;
import com.example.genericdawawalauser.databinding.FragmentWellnessBinding;


public class WellnessFragment extends Fragment {
    private FragmentWellnessBinding fragmentWellnessBinding = null;


    public WellnessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentWellnessBinding = FragmentWellnessBinding.inflate(inflater, container, false);
        setWellnessRecyclerView();
        setActivityToolbarInvisible();
        return fragmentWellnessBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setActivityToolbarInvisible();
    }

    private void setWellnessRecyclerView() {
        WellnessPackagesListAdapter wellnessPackagesListAdapter = new WellnessPackagesListAdapter();
        fragmentWellnessBinding.wellnessRecyclerview.setAdapter(wellnessPackagesListAdapter);
    }

    private void setActivityToolbarInvisible() {
        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentWellnessBinding = null;
    }
}