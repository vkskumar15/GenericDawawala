package com.example.genericdawawalauser.fragments.profiles;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.databinding.FragmentEditProfileBinding;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditProfileFragment extends Fragment {
    static int CODE_READ_EXTERNAL = 1378;
    static int PICK_IMAGE_PHOTO = 1;
    FragmentEditProfileBinding binding;
    int flag = 0;
    String gender;
    private String imgPhotosPath;
    private int mDay;
    private int mMonth;
    private int mYear;
    String selected_date;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, CODE_READ_EXTERNAL);
        onClicks();
        setData();
        return binding.getRoot();
    }

    private void setData() {
        binding.userName.setText(App.getSharedPre().getString(AppConstants.USER_NAME));
        binding.signInEmail.setText(App.getSharedPre().getString(AppConstants.USER_EMAIL));
        binding.dob.setText(App.getSharedPre().getString(AppConstants.DOB));
        binding.phoneNo.setText(App.getSharedPre().getString(AppConstants.PHONE_NUMBER));
        binding.address.setText(App.getSharedPre().getString(AppConstants.USER_ADDRESS));
        Glide.with(requireContext()).load(App.getSharedPre().getString(AppConstants.USER_IMAGE)).into(binding.imgHospitalId);
    }

    private void onClicks() {
        binding.back.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        binding.icPlusId.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_PHOTO);
        });
        binding.dob.setOnClickListener(v -> {
            openCalenser();
        });
        binding.btnUpdate.setOnClickListener(v -> {

            updateData();
        });
    }


    private void openCalenser() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(1);
        mMonth = c.get(2);
        mDay = c.get(5);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, i, i1, i2) -> {
            Date myDate = new Date();
            myDate.setMonth(i1);
            myDate.setYear(i - 1900);
            myDate.setDate(i2);
            selected_date = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
            binding.dob.setText(selected_date);
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void updateData() {
        CheckGender();

        new ViewModalClass().updateUserDataModelRootLiveData(requireActivity(),

                CommonUtils.stringToRequestBody(CommonUtils.getUserId()),
                CommonUtils.stringToRequestBody(binding.userName.getText().toString()),
                CommonUtils.stringToRequestBody(gender),
                CommonUtils.stringToRequestBody(binding.signInEmail.getText().toString()),
                CommonUtils.stringToRequestBody(binding.dob.getText().toString()),
                CommonUtils.stringToRequestBody(binding.phoneNo.getText().toString()),
                CommonUtils.stringToRequestBody(binding.address.getText().toString()),

                CommonUtils.imageToMultiPart("image", imgPhotosPath)).observe(requireActivity(), registerModelRoot -> {
            if (registerModelRoot.getSuccess().equalsIgnoreCase("1")) {

                Toast.makeText(requireActivity(), "" + registerModelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                App.getSharedPre().saveString(AppConstants.USER_ID, registerModelRoot.getDetails().getId());
                App.getSharedPre().saveModel(AppConstants.USER_DETAILS, registerModelRoot.getDetails());
                App.getSharedPre().saveString(AppConstants.USER_NAME, registerModelRoot.getDetails().getUsername());
                App.getSharedPre().saveString(AppConstants.USER_EMAIL, registerModelRoot.getDetails().getEmail());
                App.getSharedPre().saveString(AppConstants.USER_IMAGE, registerModelRoot.getDetails().getImage());
                App.getSharedPre().saveString(AppConstants.PHONE_NUMBER, registerModelRoot.getDetails().getPhone());
                App.getSharedPre().saveString(AppConstants.DOB, registerModelRoot.getDetails().getDob());
                App.getSharedPre().saveString(AppConstants.USER_ADDRESS, registerModelRoot.getDetails().getAddress());
                App.getSharedPre().saveString(AppConstants.GENDER, registerModelRoot.getDetails().getGender());
                App.getSharedPre().saveString(AppConstants.CHAT_ID, registerModelRoot.getDetails().getChatId());
                requireActivity().onBackPressed();

                return;
            }

            Toast.makeText(requireActivity(), "" + registerModelRoot.getMessage(), Toast.LENGTH_SHORT).show();

        });
    }


    public void CheckGender() {
        if (binding.radioBtnMaleEditprof.isChecked()) {
            gender = binding.radioBtnMaleEditprof.getText().toString();
        } else if (binding.radioBtnFemaleEditprof.isChecked()) {
            gender = binding.radioBtnFemaleEditprof.getText().toString();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_PHOTO && resultCode == -1) {
            Uri imageUriPhotos = data.getData();
            imgPhotosPath = uriToStringConvert(imageUriPhotos);
            binding.imgHospitalId.setImageURI(imageUriPhotos);
        }
    }

    private String uriToStringConvert(Uri newUri) {
        String[] filePathColumn = {"_data"};
        Cursor cursor = getActivity().getContentResolver().query(newUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
        cursor.close();
        Toast.makeText(getActivity(), "" + path, Toast.LENGTH_SHORT).show();
        return path;
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) == -1) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            return;
        }
        Toast.makeText(getActivity(), "Permission already granted", Toast.LENGTH_SHORT).show();
        flag = 1;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != CODE_READ_EXTERNAL) {
            return;
        }
        if (grantResults.length <= 0 || grantResults[0] != 0) {
            Toast.makeText(getActivity(), "Read Storage Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getActivity(), "Read Storage Permission Granted", Toast.LENGTH_SHORT).show();
        flag = 1;
    }
}
