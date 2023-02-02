package com.example.genericdawawalauser.fragments.onlineConsult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.Adapter_Recycler_Location;
import com.example.genericdawawalauser.databinding.FragmentHomeConsultationBinding;
import com.example.genericdawawalauser.fragments.homefragments.DoctorBySpecialitiesFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;

import java.util.ArrayList;

public class HomeConsultationFragment extends Fragment implements Adapter_Recycler_Location.Select {
    FragmentHomeConsultationBinding binding;
    Adapter_Recycler_Location adapter_recycler_location;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.binding = FragmentHomeConsultationBinding.inflate(inflater, container, false);


        adapter_recycler_location = new Adapter_Recycler_Location(getContext(), new ArrayList<>(), HomeConsultationFragment.this);
        binding.recyclerLocation.setAdapter(adapter_recycler_location);


        setData();
        searchOperation();


        return this.binding.getRoot();
    }

    private void setData() {

        binding.backArrowSelectLocation.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        new ViewModalClass().GetDoctorSpecialitiesLiveData(getActivity()).observe(requireActivity(), new Observer<DoctorModelRoot>() {
            @Override
            public void onChanged(DoctorModelRoot doctorModelRoot) {

                adapter_recycler_location.loadData(doctorModelRoot.getDetails());

                Adapter_Recycler_Location.unFilteredList = new ArrayList<>(doctorModelRoot.getDetails());

            }
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

                adapter_recycler_location.getFilter().filter(newText);

                return false;
            }
        });

    }

    @Override
    public void onClick(DoctorModelDetails doctorModelDetails) {
        DoctorBySpecialitiesFragment.doctorModelDetails = doctorModelDetails;

        Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorBySpecialitiesFragment2);
    }
}
