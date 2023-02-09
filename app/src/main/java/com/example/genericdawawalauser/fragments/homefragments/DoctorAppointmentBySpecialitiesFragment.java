package com.example.genericdawawalauser.fragments.homefragments;

import static com.example.genericdawawalauser.activities.SplashActivity.latitude;
import static com.example.genericdawawalauser.activities.SplashActivity.longitude;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.DoctorBySpecialitiesAdapter;
import com.example.genericdawawalauser.adapters.online_consultation.DrAppointmentBySpecialitiesAdapter;
import com.example.genericdawawalauser.databinding.FragmentDoctorAppointmentBySpecialitiesBinding;
import com.example.genericdawawalauser.databinding.FragmentDoctorBySpecialitiesBinding;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorDetailsFragment;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorTimeSlotFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentBySpecialitiesFragment extends Fragment {
    FragmentDoctorAppointmentBySpecialitiesBinding binding;
    public static DoctorModelDetails doctorModelDetails;
    DrAppointmentBySpecialitiesAdapter adapter;
    List<DoctorModelDetails> list = new ArrayList<>();
    String onlineStatus, offlineStatus, drStatus;
    String specialityId, locality, clinic_name;
    int specialityStatus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDoctorAppointmentBySpecialitiesBinding.inflate(inflater, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            locality = bundle.getString("locality");
            clinic_name = bundle.getString("clinic_name");
            specialityStatus = bundle.getInt("specialityStatus");
        }

        if (specialityStatus == 12) {

            filterAdapter();

        } else {

          setAdapter();


        }


        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });

        binding.filter.setOnClickListener(v -> {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.locationFragment);

        });

        searchOperation();



        return binding.getRoot();
    }

    private void filterAdapter() {

        new ViewModalClass().getFilterDoctorsAsPerSpecialityLiveData(requireActivity(), doctorModelDetails.getId(),
                locality, clinic_name).observe(requireActivity(), doctorModelRoot -> {
            if (doctorModelRoot.getSuccess().equalsIgnoreCase("1")) {
                adapter = new DrAppointmentBySpecialitiesAdapter(requireContext(), doctorModelDetails -> {
                    drStatus = "1";
                    onlineStatus = "2";
                    offlineStatus = "3";

                    App.getSingleton().setDrStatus(drStatus);

                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("drStatus", drStatus);
                    bundle.putString("online", onlineStatus);
                    bundle.putString("offline", offlineStatus);
                    fragment.setArguments(bundle);

                    DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                    App.getSingleton().setFees(doctorModelDetails.getOnline_price());
                    App.getSingleton().setDoctor_id(doctorModelDetails.getId());
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment3, bundle);


                }, doctorModelRoot.getDetails(), doctorModelDetails -> {

                    drStatus = "2";
                    onlineStatus = "2";
                    offlineStatus = "3";
                    App.getSingleton().setDrStatus(drStatus);

                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("drStatus", drStatus);
                    bundle.putString("online", onlineStatus);
                    bundle.putString("offline", offlineStatus);
                    fragment.setArguments(bundle);

                    DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                    App.getSingleton().setFees(doctorModelDetails.getOnline_price());
                    App.getSingleton().setDoctor_id(doctorModelDetails.getId());
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment3, bundle);


                }, doctorModelDetails -> {
                    DoctorDetailsFragment.doctorModelDetails = doctorModelDetails;
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorDetailsFragment2);
                });
                binding.recyclerConsult.setAdapter(adapter);
                DoctorBySpecialitiesAdapter.unFilteredList = new ArrayList<>(list);
            }
        });

    }

    private void setAdapter() {

        new ViewModalClass().getDoctorsAsPerSpecialityLiveData(requireActivity(), doctorModelDetails.getId(),String.valueOf(latitude), String.valueOf(longitude)).observe(requireActivity(), doctorModelRoot -> {
            if (doctorModelRoot.getSuccess().equalsIgnoreCase("1")) {
                adapter = new DrAppointmentBySpecialitiesAdapter(requireContext(), doctorModelDetails -> {

                    drStatus = "1";
                    onlineStatus = "2";
                    offlineStatus = "3";

                    App.getSingleton().setDrStatus(drStatus);

                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("drStatus", drStatus);
                    bundle.putString("online", onlineStatus);
                    bundle.putString("offline", offlineStatus);
                    fragment.setArguments(bundle);


                    DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                    App.getSingleton().setFees(doctorModelDetails.getOnline_price());
                    App.getSingleton().setDoctor_id(doctorModelDetails.getId());
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment3, bundle);


                }, doctorModelRoot.getDetails(), doctorModelDetails -> {

                    drStatus = "2";
                    onlineStatus = "2";
                    offlineStatus = "3";
                    App.getSingleton().setDrStatus(drStatus);

                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("drStatus", drStatus);
                    bundle.putString("online", onlineStatus);
                    bundle.putString("offline", offlineStatus);
                    fragment.setArguments(bundle);

                    DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                    App.getSingleton().setFees(doctorModelDetails.getOnline_price());
                    App.getSingleton().setDoctor_id(doctorModelDetails.getId());
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment3, bundle);


                }, doctorModelDetails -> {
                    DoctorDetailsFragment.doctorModelDetails = doctorModelDetails;
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorDetailsFragment2);
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