package com.example.genericdawawalauser.fragments.homefragments;

import static com.example.genericdawawalauser.activities.SplashActivity.latitude;
import static com.example.genericdawawalauser.activities.SplashActivity.longitude;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.DoctorBySpecialitiesAdapter;
import com.example.genericdawawalauser.adapters.online_consultation.DrAppointmentBySpecialitiesAdapter;
import com.example.genericdawawalauser.databinding.FragmentDoctorAppointmentBySpecialitiesBinding;
import com.example.genericdawawalauser.databinding.FragmentDoctorBySpecialitiesBinding;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorDetailsFragment;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorTimeSlotFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentBySpecialitiesFragment extends Fragment {
    FragmentDoctorAppointmentBySpecialitiesBinding binding;
    public static DoctorModelDetails doctorModelDetails;
    DrAppointmentBySpecialitiesAdapter adapter;
    List<DoctorModelDetails> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDoctorAppointmentBySpecialitiesBinding.inflate(inflater, container, false);


        try {
            setAdapter();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });


        searchOperation();


        return binding.getRoot();
    }

    private void setAdapter() {
        new ViewModalClass().getDoctorsAsPerSpecialityLiveData(requireActivity(),
                doctorModelDetails.getId(), String.valueOf(latitude),
                String.valueOf(longitude)).observe(requireActivity(), doctorModelRoot -> {
                    if (doctorModelRoot.getSuccess().equalsIgnoreCase("1")) {
                        adapter = new DrAppointmentBySpecialitiesAdapter(requireContext(), doctorModelDetails -> {

                            DoctorDetailsFragment.doctorModelDetails = doctorModelDetails;
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorDetailsFragment);

                        }, doctorModelRoot.getDetails(), doctorModelDetails -> {
                            DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                            App.getSingleton().setFees(doctorModelDetails.getOnline_price());
                            App.getSingleton().setDoctor_id(doctorModelDetails.getId());
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment);
                        });
                        binding.recyclerConsult.setAdapter(adapter);
                        DoctorBySpecialitiesAdapter.unFilteredList = new ArrayList<>(list);

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

                adapter.getFilter().filter(newText);

                return false;
            }
        });

    }

}