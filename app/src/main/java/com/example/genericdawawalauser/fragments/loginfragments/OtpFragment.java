package com.example.genericdawawalauser.fragments.loginfragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.SplashActivity;
import com.example.genericdawawalauser.databinding.FragmentOtpBinding;
import com.example.genericdawawalauser.modalClass.RegisterModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.google.firebase.messaging.FirebaseMessaging;


public class OtpFragment extends Fragment {
    private String android_id;
    public FragmentOtpBinding binding;
    String email;
    String name;
    String otp;
    String password;
    String phone;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater, container, false);

        android_id = Settings.Secure.getString(getContext().getContentResolver(), "android_id");

        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
            email = bundle.getString("email");
            phone = bundle.getString("phone");
            password = bundle.getString("password");
            otp = bundle.getString(AppConstants.OTP);
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    token = task.getResult();

                });


        setClicksOnViews();


        return binding.getRoot();
    }

    private void setClicksOnViews() {
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_otpFragment_to_profileVerifiedFragment);
            }
        });
        binding.submitBtn.setOnClickListener(view -> {

            userLogin();
        });
    }

    private void userLogin() {
        if (binding.otpView.getOTP().equals(otp)) {
            new ViewModalClass().registerModelRootLiveData(requireActivity(), name, email, phone, password, token, android_id, "user", String.valueOf(SplashActivity.latitude), String.valueOf(SplashActivity.longitude), "").
                    observe(requireActivity(), registerModelRoot -> {
                        if (registerModelRoot.getSuccess().equalsIgnoreCase("1")) {
                            App.getSharedPre().saveString(AppConstants.LOGIN_STATUS, "0");
                            Toast.makeText(requireActivity(), "" + registerModelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            App.getSharedPre().saveString(AppConstants.USER_ID, registerModelRoot.getDetails().getId());
                            App.getSharedPre().saveModel(AppConstants.USER_DETAILS, registerModelRoot.getDetails());
                            App.getSharedPre().saveString(AppConstants.USER_NAME, registerModelRoot.getDetails().getUsername());
                            App.getSharedPre().saveString(AppConstants.USER_IMAGE, registerModelRoot.getDetails().getImage());
                            App.getSharedPre().saveString(AppConstants.USER_EMAIL, registerModelRoot.getDetails().getEmail());
                            App.getSharedPre().saveString(AppConstants.PHONE_NUMBER, registerModelRoot.getDetails().getPhone());
                            App.getSharedPre().saveString(AppConstants.CHAT_ID, registerModelRoot.getDetails().getChatId());
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.profileVerifiedFragment);
                            return;
                        }
                        Toast.makeText(requireActivity(), "" + registerModelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(requireContext(), "Otp Not Match", Toast.LENGTH_SHORT).show();
        }
    }

}