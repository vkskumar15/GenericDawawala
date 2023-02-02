package com.example.genericdawawalauser.fragments.loginfragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import com.example.genericdawawalauser.activities.HomeActivity;
import com.example.genericdawawalauser.activities.SplashActivity;
import com.example.genericdawawalauser.databinding.FragmentLoginBinding;
import com.example.genericdawawalauser.modalClass.RegisterModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.google.firebase.messaging.FirebaseMessaging;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding = null;
    private String android_id;
    private String str_emailPhone;
    private String str_password;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    token = task.getResult();

                });

        android_id = Settings.Secure.getString(getContext().getContentResolver(), "android_id");
        setClicksOnViews();

        return binding.getRoot();
    }


    private void setClicksOnViews() {
        binding.loginBtn.setOnClickListener(view -> {
            loginUser();
        });

        binding.signUpText.setOnClickListener(view -> Navigation.findNavController(view).navigate((int) R.id.action_loginFragment_to_registerFragment));
        this.binding.forgotPassText.setOnClickListener(view -> Navigation.findNavController(view).navigate((int) R.id.action_loginFragment_to_resetPasswordFragment));
    }

    private void loginUser() {
        str_emailPhone = binding.signInEmail.getText().toString();
        str_password = binding.signInPassword.getText().toString();

        if (binding.signInEmail.getText().toString().trim().length() == 0) {

            binding.signInEmail.setError("Please enter email");

        } else if (binding.signInPassword.getText().toString().trim().length() == 0) {

            binding.signInPassword.setError("Please enter password");

        } else {

            new ViewModalClass().loginUserModelRootLiveData(requireActivity(), str_emailPhone, str_password,
                    token, "android", android_id, String.valueOf(SplashActivity.latitude),
                    String.valueOf(SplashActivity.longitude)).observe(requireActivity(), registerModelRoot -> {
                        if (registerModelRoot.getSuccess().equalsIgnoreCase("1")) {
                            Toast.makeText(requireActivity(), "" + registerModelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            App.getSharedPre().saveString(AppConstants.USER_ID, registerModelRoot.getDetails().getId());
                            App.getSharedPre().saveModel(AppConstants.USER_DETAILS, registerModelRoot.getDetails());
                            App.getSharedPre().saveString(AppConstants.USER_NAME, registerModelRoot.getDetails().getUsername());
                            App.getSharedPre().saveString(AppConstants.USER_IMAGE, registerModelRoot.getDetails().getImage());
                            App.getSharedPre().saveString(AppConstants.USER_EMAIL, registerModelRoot.getDetails().getEmail());
                            App.getSharedPre().saveString(AppConstants.CHAT_ID, registerModelRoot.getDetails().getChatId());
                            App.getSharedPre().saveString(AppConstants.PHONE_NUMBER, registerModelRoot.getDetails().getPhone());
                            App.getSharedPre().saveString(AppConstants.LOGIN_STATUS, "0");
                            startActivity(new Intent(requireContext(), HomeActivity.class));
                            return;
                        }
                        Toast.makeText(requireActivity(), "" + registerModelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }


}
