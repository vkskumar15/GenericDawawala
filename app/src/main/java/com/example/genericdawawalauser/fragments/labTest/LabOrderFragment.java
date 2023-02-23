package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentLabOrderBinding;
import com.example.genericdawawalauser.utils.App;


public class LabOrderFragment extends Fragment {
    FragmentLabOrderBinding binding;
    String quantity, total_price, total_patient, labId;
    int price;
    public static String appointmentSlot, appointmentDateToShow, appointmentDateToSend;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLabOrderBinding.inflate(inflater, container, false);

        setDetails();
        return binding.getRoot();

    }

    private void setDetails() {

        total_patient = App.getSingleton().getTotal_patient();
        total_price = App.getSingleton().getTotal_amount();
        binding.timeSlot.setText(appointmentSlot + ", " + appointmentDateToShow);

        price = Integer.parseInt(total_price) * Integer.parseInt(total_patient);
        binding.address.setText(App.getSingleton().getPatient_address_details());
    }
}