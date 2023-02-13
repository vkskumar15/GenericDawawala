package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularTestAdapter;
import com.example.genericdawawalauser.databinding.FragmentPathologyBinding;

public class PathologyFragment extends Fragment {

    FragmentPathologyBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentPathologyBinding.inflate(inflater, container, false);

       setPopularAdapter();


       return binding.getRoot();

    }

    private void setPopularAdapter() {

        LabPopularTestAdapter adapter = new LabPopularTestAdapter();
        binding.recyclerviewCondition.setAdapter(adapter);
    }
}