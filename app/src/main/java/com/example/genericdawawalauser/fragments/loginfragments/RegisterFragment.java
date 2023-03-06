package com.example.genericdawawalauser.fragments.loginfragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentRegisterBinding;
import com.example.genericdawawalauser.modalClass.UniqueAPiModel;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.AppConstants;


public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private String str_confirm_password;
    public String str_email;
    public String str_password;
    public String str_phone;
    public String str_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        setClicksOnViews();

        return binding.getRoot();
    }


    private void setClicksOnViews() {
        binding.tvSignin.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.loginFragment);
        });
        binding.btnRegister.setOnClickListener(view -> {
            registerUser();
        });
    }

    private void registerUser() {
        str_user = binding.userName.getText().toString();
        str_email = binding.signInEmail.getText().toString();
        str_phone = binding.phoneNo.getText().toString();
        str_password = binding.password.getText().toString();
        str_confirm_password = binding.confirmPassword.getText().toString();
        if (binding.userName.length() == 0) {
            Toast.makeText(getContext(), "Please enter user name", Toast.LENGTH_SHORT).show();
        } else if (binding.signInEmail.length() == 0) {
            Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()) {
            Toast.makeText(getContext(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
        } else if (binding.phoneNo.length() == 0) {
            Toast.makeText(getContext(), "Please enter phone number", Toast.LENGTH_SHORT).show();
        } else if (!phonePatternChecker(str_phone)) {
            Toast.makeText(getContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
        } else if (binding.password.length() == 0) {
            Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_SHORT).show();
        } else if (binding.confirmPassword.length() == 0) {
            Toast.makeText(getContext(), "Please enter confirm password", Toast.LENGTH_SHORT).show();
        } else if (!str_password.equals(str_confirm_password)) {
            Toast.makeText(getContext(), "Your password and confirm password doesn't match", Toast.LENGTH_SHORT).show();
        } else if (!binding.switchAccept.isOn()) {
            Toast.makeText(getContext(), "Please accept terms and conditions of health kangaroo", Toast.LENGTH_SHORT).show();
        } else {
            new ViewModalClass().uniqueAPiModelLiveData(requireActivity(), str_email, str_phone).observe(requireActivity(), uniqueAPiModel -> {
                if (uniqueAPiModel.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "" + uniqueAPiModel.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(requireActivity(), "" + uniqueAPiModel.getOtp(), Toast.LENGTH_SHORT).show();
                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name", str_user);
                    bundle.putString("email", str_email);
                    bundle.putString("phone", str_phone);
                    bundle.putString("password", str_password);
                    bundle.putString(AppConstants.OTP, uniqueAPiModel.getOtp());
                    fragment.setArguments(bundle);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.otpFragment, bundle);
                    return;
                }
                Toast.makeText(requireActivity(), "" + uniqueAPiModel.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
    }


    public boolean phonePatternChecker(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }
}