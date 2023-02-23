package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.AddToCartAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabTestOrderAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabOrderBinding;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class LabOrderFragment extends Fragment {
    FragmentLabOrderBinding binding;
    String quantity, total_price, total_patient, labId, total_patient_id, cart_total_item, address_id;
    int price;
    public static String appointmentSlot, appointmentDateToShow, appointmentDateToSend;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLabOrderBinding.inflate(inflater, container, false);

        setDetails();
        setAdapter();

        return binding.getRoot();

    }

    private void setDetails() {

        total_patient = App.getSingleton().getTotal_patient();
        total_price = App.getSingleton().getTotal_amount();
        binding.timeSlot.setText(appointmentSlot + ", " + appointmentDateToShow);
        total_patient_id = App.getSingleton().getPatient_details();
        cart_total_item = App.getSharedPre().getString(AppConstants.TOTAL_TEST);
        labId = App.getSingleton().getLabId();
        address_id = App.getSingleton().getPatient_address();

        Toast.makeText(requireActivity(), ""+cart_total_item, Toast.LENGTH_SHORT).show();
        price = Integer.parseInt(total_price) * Integer.parseInt(total_patient);
        binding.address.setText(App.getSingleton().getPatient_address_details());

        binding.totalAmount.setText("₹"+price);
        binding.totalPaid.setText("₹"+price);
        binding.amount.setText("₹"+price);

    }

    private void setAdapter() {
        new ViewModalClass().addCartLabModalLiveData(getActivity(), CommonUtils.getUserId(), labId).observe(getActivity(),
                addCartLabModal -> {
            if (addCartLabModal.getSuccess().equalsIgnoreCase("1")) {


                LabTestOrderAdapter adapter = new LabTestOrderAdapter(addCartLabModal.getDetails(), getActivity());
                binding.recyclerView.setAdapter(adapter);

            } else {
                Toast.makeText(getActivity(), "" + addCartLabModal.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}