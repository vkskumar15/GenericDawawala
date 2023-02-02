package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.TestConditionListAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabTestBinding;


public class LabTestFragment extends Fragment {
    private FragmentLabTestBinding fragmentLabTestBinding = null;

    public LabTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLabTestBinding = FragmentLabTestBinding.inflate(inflater, container, false);
        setLabTestRecyclerViews();
        return fragmentLabTestBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setActivityToolbarInvisible();
    }


    private void setActivityToolbarInvisible() {
        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }

    private void setLabTestRecyclerViews() {
        PathologyTestListAdapter pathologyTestListAdapter = new PathologyTestListAdapter();
        fragmentLabTestBinding.pathologyTestRecyclerview.setAdapter(pathologyTestListAdapter);

        TestConditionListAdapter testConditionListAdapter = new TestConditionListAdapter();
        fragmentLabTestBinding.testConditionsRecyclerview.setAdapter(testConditionListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentLabTestBinding = null;
    }
}