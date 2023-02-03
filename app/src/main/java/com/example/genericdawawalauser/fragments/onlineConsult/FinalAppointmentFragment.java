package com.example.genericdawawalauser.fragments.onlineConsult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentFinalAppointmentBinding;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.utils.App;


public class FinalAppointmentFragment extends Fragment {

    FragmentFinalAppointmentBinding binding;
    public static DoctorModelDetails doctorModelDetails;
    public static  String appointmentSlot, appointmentDateToShow, appointmentDateToSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFinalAppointmentBinding.inflate(inflater, container, false);

        setDetails();
        return binding.getRoot();

    }

    private void setDetails() {

        Glide.with(requireActivity()).load(doctorModelDetails.getDoctorImage()).into(binding.imgProfileReview);
        binding.docNameReview.setText(doctorModelDetails.getName());
        binding.docSpecialityAndQualification.setText(doctorModelDetails.getQualificationTitle());
        binding.appointmentdate.setText(appointmentSlot+" "+appointmentDateToShow);
        binding.name.setText(App.getSingleton().getName());
        binding.relation.setText(App.getSingleton().getRelation());
        binding.age.setText(App.getSingleton().getAge()+" Years");
        binding.gender.setText(App.getSingleton().getGender());
        binding.healthProblem.setText(App.getSingleton().getProblem());
        binding.number.setText(App.getSingleton().getNumber());
        binding.totalPaid.setText(App.getSingleton().getFees());
        binding.totalAmount.setText(App.getSingleton().getFees());
        binding.amount.setText(App.getSingleton().getFees());


    }
}