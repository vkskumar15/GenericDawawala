package com.example.genericdawawalauser.fragments.loginfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.AdapterHealthProblem;
import com.example.genericdawawalauser.databinding.FragmentHealthProblemOnlineBinding;
import com.example.genericdawawalauser.modalClass.HealthProblemModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;

import java.util.ArrayList;

public class HealthProblemOnlineFragment extends Fragment implements AdapterHealthProblem.Select {
    FragmentHealthProblemOnlineBinding binding;
    AdapterHealthProblem adapterHealthProblem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHealthProblemOnlineBinding.inflate(inflater, container, false);
        adapterHealthProblem = new AdapterHealthProblem(getContext(), new ArrayList<>(), HealthProblemOnlineFragment.this);

        binding.recyclerLocation.setAdapter(adapterHealthProblem);

        setData();

        searchOperation();


        return this.binding.getRoot();
    }

    private void setData() {
        binding.back.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        new ViewModalClass().healthProblemModalLiveData(getActivity()).observe(requireActivity(), doctorModelRoot -> {

            adapterHealthProblem.loadData(doctorModelRoot.getDetails());
            adapterHealthProblem.unFilteredList = new ArrayList<>(doctorModelRoot.getDetails());
        });
    }

    private void searchOperation() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapterHealthProblem.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onClick(HealthProblemModal.Detail doctorModelDetails) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.homeConsultationFragment);
        App.getSingleton().setHealthProblem(doctorModelDetails.getTitle());

    }
}