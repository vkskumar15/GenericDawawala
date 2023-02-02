package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentProfileBinding;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);


        setData();

        binding.profile.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(R.id.personalDetailsFragment);
        });
        return binding.getRoot();
    }

    private void setData() {
        binding.name.setText(App.getSharedPre().getString(AppConstants.USER_NAME));
        binding.email.setText(App.getSharedPre().getString(AppConstants.USER_EMAIL));
        binding.phoneNo.setText(App.getSharedPre().getString(AppConstants.PHONE_NUMBER));
        binding.dob.setText(App.getSharedPre().getString(AppConstants.DOB));
        Glide.with(requireContext()).load(App.getSharedPre().getString(AppConstants.USER_IMAGE)).into(binding.profileImage);
    }

}