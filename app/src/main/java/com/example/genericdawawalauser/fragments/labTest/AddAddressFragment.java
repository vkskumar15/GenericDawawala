package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentAddAddressBinding;
import com.example.genericdawawalauser.modalClass.AddPatientDetails;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;


public class AddAddressFragment extends Fragment {
    FragmentAddAddressBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddAddressBinding.inflate(inflater, container, false);


        addDetails();
        return binding.getRoot();
    }

    private void addDetails() {

        binding.btnNext.setOnClickListener(view -> {

            String name = binding.name.getText().toString();
            String mobile = binding.number.getText().toString();
            String pin = binding.pinCode.getText().toString();
            String state = binding.state.getText().toString();
            String city = binding.city.getText().toString();
            String address = binding.fullAddress.getText().toString();
            String locality = binding.locality.getText().toString();


            if (name.isEmpty()) {
                binding.name.setError("Enter name");
            } else if (mobile.isEmpty()) {
                binding.number.setError("Enter Number");

            } else if (pin.isEmpty()) {
                binding.pinCode.setError("Enter Pin Code");

            } else if (state.isEmpty()) {
                binding.state.setError("Enter State");

            } else if (city.isEmpty()) {
                binding.city.setError("Enter City");

            } else if (address.isEmpty()) {
                binding.fullAddress.setError("Enter Address");

            } else if (locality.isEmpty()) {
                binding.locality.setError("Enter Locality");

            } else {

                new ViewModalClass().addPatientDetailsLiveData(requireActivity(), CommonUtils.getUserId(), name, mobile
                        , pin, state, city, address, locality).observe(requireActivity(), new Observer<AddPatientDetails>() {
                    @Override
                    public void onChanged(AddPatientDetails addPatientDetails) {
                        if (addPatientDetails.getSuccess().equalsIgnoreCase("1")) {
                            requireActivity().onBackPressed();
                            Toast.makeText(requireActivity(), "" + addPatientDetails.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "" + addPatientDetails.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

        });
    }
}