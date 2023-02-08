package com.example.genericdawawalauser.fragments.drAppointment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.Adapter_Recycler_Location;
import com.example.genericdawawalauser.databinding.FragmentDrAppointmentBinding;
import com.example.genericdawawalauser.databinding.FragmentSpecilityBinding;
import com.example.genericdawawalauser.fragments.homefragments.DoctorAppointmentBySpecialitiesFragment;
import com.example.genericdawawalauser.fragments.homefragments.DoctorBySpecialitiesFragment;
import com.example.genericdawawalauser.fragments.onlineConsult.HomeConsultationFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;

import java.util.ArrayList;


public class SpecialtyFragment extends Fragment implements Adapter_Recycler_Location.Select {
    FragmentSpecilityBinding binding;
    Adapter_Recycler_Location adapter_recycler_location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSpecilityBinding.inflate(inflater, container, false);

        adapter_recycler_location = new Adapter_Recycler_Location(getContext(), new ArrayList<>(), SpecialtyFragment.this);
        binding.recyclerLocation.setAdapter(adapter_recycler_location);

        setData();
        searchOperation();


        return this.binding.getRoot();
    }

    private void setData() {

        binding.back.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        new ViewModalClass().GetDoctorSpecialitiesLiveData(getActivity()).observe(requireActivity(), doctorModelRoot -> {

            adapter_recycler_location.loadData(doctorModelRoot.getDetails());

            Adapter_Recycler_Location.unFilteredList = new ArrayList<>(doctorModelRoot.getDetails());

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
        DoctorAppointmentBySpecialitiesFragment.doctorModelDetails = doctorModelDetails;
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_specialtyFragment_to_doctorAppointmentBySpecialitiesFragment);

        App.getSingleton().setProblem(doctorModelDetails.getSpecialty());


    }
}