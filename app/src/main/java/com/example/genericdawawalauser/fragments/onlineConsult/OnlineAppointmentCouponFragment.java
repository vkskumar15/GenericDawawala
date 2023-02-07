package com.example.genericdawawalauser.fragments.onlineConsult;

import static com.example.genericdawawalauser.fragments.onlineConsult.FinalAppointmentFragment.doctorId;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.adapters.online_consultation.CouponAndOfferAdapter;
import com.example.genericdawawalauser.databinding.FragmentOnlineAppointmentCouponBinding;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;

public class OnlineAppointmentCouponFragment extends Fragment {
    FragmentOnlineAppointmentCouponBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOnlineAppointmentCouponBinding.inflate(inflater, container, false);


        setAdapter();

        return binding.getRoot();

    }

    private void setAdapter() {
        new ViewModalClass().onlineAppointmentCouponModalLiveData(requireActivity(), doctorId).observe(requireActivity(), onlineAppointmentCouponModal -> {
            if (onlineAppointmentCouponModal.getSuccess().equalsIgnoreCase("1")) {
                CouponAndOfferAdapter adapter = new CouponAndOfferAdapter(requireActivity(), onlineAppointmentCouponModal.getDetails(), detail -> {

                    App.getSingleton().setCouponCode(detail.getCouponName());
                    requireActivity().onBackPressed();
                });
                binding.recyclerView.setAdapter(adapter);
            } else {

                Toast.makeText(requireActivity(), "" + onlineAppointmentCouponModal.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}