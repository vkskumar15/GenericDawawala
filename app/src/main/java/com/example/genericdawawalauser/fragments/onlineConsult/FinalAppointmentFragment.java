package com.example.genericdawawalauser.fragments.onlineConsult;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.HomeActivity;
import com.example.genericdawawalauser.activities.WalletActivity;
import com.example.genericdawawalauser.databinding.FragmentFinalAppointmentBinding;
import com.example.genericdawawalauser.modalClass.ApplyCouponAppointment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.TimeSlotsModels.OnlineAppointmentModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;


public class FinalAppointmentFragment extends Fragment {

    FragmentFinalAppointmentBinding binding;
    public static DoctorModelDetails doctorModelDetails;
    public static String appointmentSlot, appointmentDateToShow, appointmentDateToSend;
    public static String doctorId, coupanVerifiedId;
    //  String  afterDiscount, totalAmount;
    int afterDiscount, totalAmount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFinalAppointmentBinding.inflate(inflater, container, false);

        setDetails();

        onClicks();


        return binding.getRoot();

    }

    private void onClicks() {
        binding.bookNow.setOnClickListener(v -> {
            bookAppointment();
        });


        binding.couponLayout.setOnClickListener(v -> {

            doctorId = doctorModelDetails.getId();
            Navigation.findNavController(v).navigate(R.id.action_finalAppointmentFragment_to_onlineAppointmentCouponFragment);
        });

        binding.btnApply.setOnClickListener(v -> {

            new ViewModalClass().applyCouponAppointmentLiveData(requireActivity(), App.getSingleton().getCouponCode(), App.getSingleton().getFees(), CommonUtils.getUserId()).observe(requireActivity(), new Observer<ApplyCouponAppointment>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onChanged(ApplyCouponAppointment applyCouponAppointment) {
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
                }
            });
        });

    }

    private void bookAppointment() {

        if (afterDiscount > 0) {
            totalAmount = afterDiscount;
        } else {

            totalAmount = Integer.parseInt(App.getSingleton().getFees());
        }


        if (!binding.checkBoxTerms.isChecked()) {

            Toast.makeText(requireActivity(), "Please accept our terms & conditions", Toast.LENGTH_SHORT).show();

        } else {
            new ViewModalClass().onlineAppointmentModalLiveData(requireActivity(),
                    CommonUtils.getUserId(), doctorModelDetails.getId(),
                    App.getSingleton().getRelation(), App.getSingleton().getGender(), App.getSingleton().getName(),
                    App.getSingleton().getAge(), App.getSingleton().getNumber(),
                    App.getSingleton().getProblem(), appointmentSlot + " " + appointmentDateToShow,
                    String.valueOf(totalAmount), coupanVerifiedId).observe(requireActivity(), onlineAppointmentModal -> {
                if (onlineAppointmentModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "" + onlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();
                    confirmationPopUp();

                } else {
                    Toast.makeText(requireActivity(), "" + onlineAppointmentModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    private void setDetails() {
        if (binding.couponName == null) {
            binding.couponName.setText("N/A");
        } else {
            binding.couponName.setText("Coupon Code: " + App.getSingleton().getCouponCode());

        }

        Glide.with(requireActivity()).load(doctorModelDetails.getDoctorImage()).into(binding.imgProfileReview);
        binding.docNameReview.setText(doctorModelDetails.getName());
        binding.docSpecialityAndQualification.setText(doctorModelDetails.getQualificationTitle());
        binding.appointmentdate.setText(appointmentSlot + " " + appointmentDateToShow);
        binding.name.setText(App.getSingleton().getName());
        binding.relation.setText(App.getSingleton().getRelation());
        binding.age.setText(App.getSingleton().getAge() + " Years");
        binding.gender.setText(App.getSingleton().getGender());
        binding.healthProblem.setText(App.getSingleton().getProblem());
        binding.number.setText(App.getSingleton().getNumber());
        binding.totalPaid.setText("₹ " + App.getSingleton().getFees());
        binding.totalAmount.setText("₹ " + App.getSingleton().getFees());
        binding.amount.setText("₹ " + App.getSingleton().getFees());


    }

    private void confirmationPopUp() {


        Dialog order_confirmation_box = new Dialog(requireActivity());
        order_confirmation_box.setContentView(R.layout.order_confirmation_dialogue_box);
        order_confirmation_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = order_confirmation_box.getWindow();
        order_confirmation_box.setCanceledOnTouchOutside(false);
        order_confirmation_box.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        order_confirmation_box.show();

        TextView text_sure = order_confirmation_box.findViewById(R.id.text_sure);
        text_sure.setText("Your online appointment booked successfully");

        order_confirmation_box.findViewById(R.id.ok_btn).setOnClickListener(view -> {

            Intent intent = new Intent(requireActivity(), HomeActivity.class);
            startActivity(intent);


        });

    }
}