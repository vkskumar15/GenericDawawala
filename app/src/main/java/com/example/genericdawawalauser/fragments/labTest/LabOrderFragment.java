package com.example.genericdawawalauser.fragments.labTest;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.HomeActivity;
import com.example.genericdawawalauser.adapters.labAdapter.AddToCartAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabTestOrderAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabOrderBinding;
import com.example.genericdawawalauser.modalClass.LabBookModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.example.genericdawawalauser.utils.CommonUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LabOrderFragment extends Fragment {
    FragmentLabOrderBinding binding;
    String imagePath, total_price, total_patient, labId, total_patient_id, cart_total_item, address_id;
    int price;
    public static String appointmentSlot, appointmentDateToShow, appointmentDateToSend;
    String stringPhotoPath;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLabOrderBinding.inflate(inflater, container, false);

        setDetails();
        setAdapter();

        if (Objects.equals(App.getSingleton().getPrescriptionCheck(), "1")) {

            binding.prescriptionsLayout.setVisibility(View.VISIBLE);

        } else {

            binding.prescriptionsLayout.setVisibility(View.GONE);

        }

        binding.prescriptionsLayout.setOnClickListener(v -> {

            try {
                if (ContextCompat.checkSelfPermission(requireActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    } else {
                        ActivityCompat.requestPermissions(requireActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                12);
                    }
                } else {
                    openGallery();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        return binding.getRoot();

    }

    private void openGallery() {

        ImagePicker.with(this).galleryOnly().cropSquare().compress(1024).start();

    }

    private void setDetails() {

        total_patient = App.getSingleton().getTotal_patient();
        total_price = App.getSingleton().getTotal_amount();
        binding.timeSlot.setText(appointmentSlot + ", " + appointmentDateToShow);
        total_patient_id = App.getSingleton().getPatient_details();
        cart_total_item = App.getSharedPre().getString(AppConstants.TOTAL_TEST);
        labId = App.getSingleton().getLabId();
        address_id = App.getSingleton().getPatient_address();
        price = Integer.parseInt(total_price) * Integer.parseInt(total_patient);
        binding.address.setText(App.getSingleton().getPatient_address_details());

        binding.totalAmount.setText("₹" + price);
        binding.totalPaid.setText("₹" + price);
        binding.amount.setText("₹" + price);

        binding.bookNow.setOnClickListener(view -> {

            if (Objects.equals(App.getSingleton().getPrescriptionCheck(), "1")) {

                if (stringPhotoPath != null) {
                    bookAppointment();
                } else {
                    Toast.makeText(requireActivity(), "Please Upload Prescription First!", Toast.LENGTH_SHORT).show();
                }

            } else {

                bookAppointment();
            }

        });
    }

    private void bookAppointment() {
        new ViewModalClass().labBookModalLiveData(requireActivity(),
                CommonUtils.stringToRequestBody(labId),
                CommonUtils.stringToRequestBody(CommonUtils.getUserId()),
                CommonUtils.stringToRequestBody(total_patient_id),
                CommonUtils.stringToRequestBody("1"),
                CommonUtils.imageToMultiPart("prescription", stringPhotoPath),
                CommonUtils.stringToRequestBody(String.valueOf(price)),
                CommonUtils.stringToRequestBody(address_id),
                CommonUtils.stringToRequestBody(appointmentDateToShow),
                CommonUtils.stringToRequestBody(appointmentSlot),
                CommonUtils.stringToRequestBody(App.getSingleton().getHomeCollectionCheck()),
                CommonUtils.stringToRequestBody(cart_total_item)
        ).observe(requireActivity(), new Observer<LabBookModal>() {
            @Override
            public void onChanged(LabBookModal labBookModal) {
                if (labBookModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "" + labBookModal.getMessage(), Toast.LENGTH_SHORT).show();
                    confirmationPopUp();

                } else {
                    Toast.makeText(requireActivity(), "" + labBookModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
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
        text_sure.setText("Your Lab Test Booked successfully");

        order_confirmation_box.findViewById(R.id.ok_btn).setOnClickListener(view -> {

            Intent intent = new Intent(requireActivity(), HomeActivity.class);
            startActivity(intent);


        });

    }

    private void setAdapter() {
        new ViewModalClass().addCartLabModalLiveData(getActivity(), CommonUtils.getUserId(), labId).observe(getActivity(), addCartLabModal -> {

            if (addCartLabModal.getSuccess().equalsIgnoreCase("1")) {

                LabTestOrderAdapter adapter = new LabTestOrderAdapter(addCartLabModal.getDetails(), getActivity());

                binding.recyclerView.setAdapter(adapter);

            } else {

                Toast.makeText(getActivity(), "" + addCartLabModal.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri imageUriPhotos = data.getData();
            stringPhotoPath = imageUriPhotos.getPath();
            binding.prescriptionsUpload.setText(stringPhotoPath);
            binding.prescriptionImage.setImageURI(imageUriPhotos);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Image Uploading Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}