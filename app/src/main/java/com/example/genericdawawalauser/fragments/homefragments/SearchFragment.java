package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.MedicineListAdapter;
import com.example.genericdawawalauser.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding fragmentSearchBinding = null;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        setSearchMedicineRecyclerView();
        return fragmentSearchBinding.getRoot();
    }

    private void setSearchMedicineRecyclerView() {
        MedicineListAdapter medicineListAdapter = new MedicineListAdapter();
        fragmentSearchBinding.medicineListRecyclerview.setAdapter(medicineListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentSearchBinding = null;
    }
}