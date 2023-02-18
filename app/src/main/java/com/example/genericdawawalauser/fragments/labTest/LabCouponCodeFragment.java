package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.CouponAndOfferAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabCouponCodeBinding;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentCouponModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;


public class LabCouponCodeFragment extends Fragment {
    FragmentLabCouponCodeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentLabCouponCodeBinding.inflate(inflater, container, false);

       binding.back.setOnClickListener(v -> {

           requireActivity().onBackPressed();

       });

       setAdapter();



       return binding.getRoot();
    }

    private void setAdapter() {

        new ViewModalClass().getLabCouponModalLiveData(requireActivity(), "2").observe(requireActivity(), onlineAppointmentCouponModal -> {
            if (onlineAppointmentCouponModal.getSuccess().equalsIgnoreCase("1"))
            {
                Toast.makeText(requireActivity(), ""+onlineAppointmentCouponModal.getMessage(), Toast.LENGTH_SHORT).show();

                CouponAndOfferAdapter adapter = new CouponAndOfferAdapter(requireActivity(),
                        onlineAppointmentCouponModal.getDetails(), new CouponAndOfferAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClick(OnlineAppointmentCouponModal.Detail detail) {
                        App.getSingleton().setCouponCode(detail.getCouponName());
                        requireActivity().onBackPressed();
                    }
                });

                binding.recyclerView.setAdapter(adapter);
            }else
            {
                Toast.makeText(requireActivity(), "Coupon not found", Toast.LENGTH_SHORT).show();
            }
        });

    }
}