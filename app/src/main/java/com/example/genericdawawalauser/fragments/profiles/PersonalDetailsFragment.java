package com.example.genericdawawalauser.fragments.profiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentPersonlDetailsBinding;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;

public class PersonalDetailsFragment extends Fragment {
    FragmentPersonlDetailsBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         binding = FragmentPersonlDetailsBinding.inflate(inflater, container, false);
        onClicks();
        setData();
        return  binding.getRoot();
    }

    private void setData() {
         binding.name.setText(App.getSharedPre().getString(AppConstants.USER_NAME));
         binding.email.setText(App.getSharedPre().getString(AppConstants.USER_EMAIL));
         binding.dob.setText(App.getSharedPre().getString(AppConstants.DOB));
         binding.gender.setText(App.getSharedPre().getString(AppConstants.GENDER));
         binding.phoneNo.setText(App.getSharedPre().getString(AppConstants.PHONE_NUMBER));
         binding.address.setText(App.getSharedPre().getString(AppConstants.USER_ADDRESS));
        Glide.with(requireContext()).load(App.getSharedPre().getString(AppConstants.USER_IMAGE)).into((ImageView)  binding.profileImage);
    }

    private void onClicks() {
         binding.back.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
         binding.edit.setOnClickListener(v -> {
          Navigation.findNavController( binding.getRoot()).navigate(R.id.editProfileFragment);
        });
         binding.btnChangePassword.setOnClickListener(v -> {
          //  Navigation.findNavController( binding.getRoot()).navigate((int) C0417R.C0420id.changePasswordFragment);
        });
    }


}
