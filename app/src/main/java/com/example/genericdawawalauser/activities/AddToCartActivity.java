package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.AddToCartAdapter;
import com.example.genericdawawalauser.databinding.ActivityAddToCartBinding;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;

public class AddToCartActivity extends Fragment {
    ActivityAddToCartBinding binding;
    String id, amount, discountPrice, dis_per, total_amount;
    private static String doctorId, coupanVerifiedId, couponAmount;
    int afterDiscount, totalAmount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityAddToCartBinding.inflate(inflater, container, false);

        onClicks();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
            amount = bundle.getString("amount");
            discountPrice = bundle.getString("discountPrice");
            dis_per = bundle.getString("dis_per");
            total_amount = bundle.getString("total_amount");
        }

        setDetails();
        setAdapter();

        return binding.getRoot();

    }

    private void setDetails() {
        binding.couponName.setText("Coupon Code: " + App.getSingleton().getCouponCode());
        binding.totalAmount.setText("₹ " + total_amount);
        binding.totalPaid.setText("₹ " + total_amount);
        binding.amount.setText("₹ " + total_amount);

        binding.couponLayout.setOnClickListener(v -> {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addToCartActivity_to_labCouponCodeFragment);
        });

        binding.btnApply.setOnClickListener(v -> {

            new ViewModalClass().labCouponAppointmentLiveData(requireActivity(), App.getSingleton().getCouponCode(), total_amount, CommonUtils.getUserId()).observe(requireActivity(), applyCouponAppointment -> {
                if (applyCouponAppointment.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "Coupon code Applied", Toast.LENGTH_SHORT).show();
                    binding.totalPaid.setText("₹ " + applyCouponAppointment.getDetails().getPayAmount());
                    binding.amount.setText("₹ " + applyCouponAppointment.getDetails().getPayAmount());
                    binding.tvDiscount.setText("₹ " + applyCouponAppointment.getDetails().getDiscountPrice());
                    binding.btnApply.setText("Applied");
                    coupanVerifiedId = applyCouponAppointment.getDetails().getVerifiedId();
                    afterDiscount = Integer.parseInt(applyCouponAppointment.getDetails().getPayAmount());
                } else {
                    Toast.makeText(requireActivity(), "" + applyCouponAppointment.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void setAdapter() {
        new ViewModalClass().addCartLabModalLiveData(getActivity(), CommonUtils.getUserId(), id).observe(getActivity(), addCartLabModal -> {
            if (addCartLabModal.getSuccess().equalsIgnoreCase("1")) {
                binding.test.setText("Pathology test: " + addCartLabModal.getDetails().size());
                AddToCartAdapter adapter = new AddToCartAdapter(addCartLabModal.getDetails(), getActivity(),
                        detail -> new AlertDialog.Builder(getActivity()).
                                setTitle("Remove Cart").
                                setMessage("Are you sure you want to remove this item?")
                                .setPositiveButton(android.R.string.yes, (dialog, which) -> new
                                        ViewModalClass().removeCartModalLiveData(getActivity(),
                                        CommonUtils.getUserId(), id).observe(getActivity(),
                                        removeCartModal -> {
                            if (removeCartModal.getSuccess().equalsIgnoreCase("1")) {
                                Toast.makeText(getActivity(), "" + removeCartModal.getMessage(), Toast.LENGTH_SHORT).show();

                                setAdapter();

                            } else {
                                Toast.makeText(getActivity(), "" + removeCartModal.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }))

                        .setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss()).setIcon(android.R.drawable.ic_dialog_alert).show());
                binding.recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "" + addCartLabModal.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void onClicks() {
        binding.bookNow.setOnClickListener(view -> {
            if (afterDiscount > 0) {
                totalAmount = afterDiscount;
            } else {
                totalAmount = Integer.parseInt(total_amount);
            }

            App.getSingleton().setTotal_amount(String.valueOf(totalAmount));
            Navigation.findNavController(view).navigate(R.id.getPatientFragment);

        });

        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });
    }
}