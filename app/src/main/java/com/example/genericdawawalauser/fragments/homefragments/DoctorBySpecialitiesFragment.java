package com.example.genericdawawalauser.fragments.homefragments;

import static com.example.genericdawawalauser.activities.SplashActivity.latitude;
import static com.example.genericdawawalauser.activities.SplashActivity.longitude;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.DoctorBySpecialitiesAdapter;
import com.example.genericdawawalauser.databinding.FragmentDoctorBySpecialitiesBinding;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorDetailsFragment;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorTimeSlotFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;

public class DoctorBySpecialitiesFragment extends Fragment {
    FragmentDoctorBySpecialitiesBinding binding;
    public static DoctorModelDetails doctorModelDetails;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDoctorBySpecialitiesBinding.inflate(inflater, container, false);


        setAdapter();

        binding.backArrowConsultPhysician.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        return binding.getRoot();
    }

    private void setAdapter() {

        new ViewModalClass().getDoctorsAsPerSpecialityLiveData(requireActivity(), doctorModelDetails.getId(), String.valueOf(latitude),String.valueOf(longitude)).observe(requireActivity(), new Observer<DoctorModelRoot>() {
            @Override
            public void onChanged(DoctorModelRoot doctorModelRoot) {
                if (doctorModelRoot.getSuccess().equalsIgnoreCase("1"))
                {
                    DoctorBySpecialitiesAdapter adapter = new DoctorBySpecialitiesAdapter(requireContext(), doctorModelDetails -> {

                        DoctorDetailsFragment.doctorModelDetails =doctorModelDetails;

                        Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorDetailsFragment);
                    }, doctorModelRoot.getDetails(), doctorModelDetails -> {
                        DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment);
                    });
                    binding.recyclerConsult.setAdapter(adapter);
                }
            }
        });
    }


}