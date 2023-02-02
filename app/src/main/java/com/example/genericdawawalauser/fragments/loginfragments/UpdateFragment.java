package com.example.genericdawawalauser.fragments.loginfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentUpdateBinding;
import com.example.genericdawawalauser.modalClass.ChangePasswordModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.AppConstants;


public class UpdateFragment extends Fragment {
    FragmentUpdateBinding binding;
    String otp;
    String otp_str;
    String password;
    String str_confirm_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        onClicks();
        Bundle bundle = getArguments();
        if (bundle != null) {
            otp = bundle.getString(AppConstants.OTP);
        }
        return binding.getRoot();

    }

    private void onClicks() {
        binding.back.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
        binding.saveBtn.setOnClickListener(v -> {
            upDatePassword();

        });
    }

    private void upDatePassword() {

        otp_str = binding.otp.getText().toString();
        password = binding.password.getText().toString();
        str_confirm_password = binding.confirmPassword.getText().toString();
        if (binding.password.length() == 0) {
            Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_SHORT).show();
        } else if (binding.confirmPassword.length() == 0) {
            Toast.makeText(getContext(), "Please enter confirm password", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(str_confirm_password)) {
            Toast.makeText(getContext(), "Your password and confirm password doesn't match", Toast.LENGTH_SHORT).show();
        } else if (otp_str.equals(otp)) {
            new ViewModalClass().changePasswordModalLiveData(requireActivity(), password).observe(requireActivity(), new Observer<ChangePasswordModal>() {
                public void onChanged(ChangePasswordModal changePasswordModal) {
                    if (changePasswordModal.getSuccess().equalsIgnoreCase("1")) {
                        Toast.makeText(requireActivity(), "" + changePasswordModal.getMessage(), Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment);
                        return;
                    }
                    Toast.makeText(requireActivity(), "" + changePasswordModal.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(requireContext(), "Otp Not Match", 0).show();
        }
    }
}