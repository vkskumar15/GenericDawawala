package com.example.genericdawawalauser.fragments.drAppointment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentLocationBinding;


public class LocationFragment extends Fragment {
    FragmentLocationBinding binding;
     public static  String specialityId, specialityStatus, locality, clinic_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);

        onClick();

        return binding.getRoot();
    }

    private void onClick() {
        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });

        binding.btnApply.setOnClickListener(v -> {

             locality = binding.locality.getText().toString();
             clinic_name = binding.clinicName.getText().toString();

            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("locality", locality);
            bundle.putString("clinic_name", clinic_name);
            bundle.putInt("specialityStatus", 12);
            fragment.setArguments(bundle);

            Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorAppointmentBySpecialitiesFragment, bundle);

        });
    }
}