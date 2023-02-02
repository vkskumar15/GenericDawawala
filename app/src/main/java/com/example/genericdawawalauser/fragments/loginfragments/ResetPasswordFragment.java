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
import com.example.genericdawawalauser.databinding.FragmentResetPasswordBinding;
import com.example.genericdawawalauser.modalClass.UpdateUserPhoneModel;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.AppConstants;


public class ResetPasswordFragment extends Fragment {
    private FragmentResetPasswordBinding binding = null;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);

        setClicksOnViews();

        return binding.getRoot();
    }


    private void setClicksOnViews() {

        this.binding.back.setOnClickListener(v -> {

            requireActivity().onBackPressed();
        });
        this.binding.saveBtn.setOnClickListener(v -> {
            updatePassword();
        });
    }

    private void updatePassword() {

        String obj = this.binding.email.getText().toString();
        this.email = obj;
        if (obj.isEmpty()) {
            this.binding.email.setError("Please Enter Email Address");
        } else {
            new ViewModalClass().updateUserPhoneModelLiveData(requireActivity(), this.email).observe(requireActivity(), new Observer<UpdateUserPhoneModel>() {
                public void onChanged(UpdateUserPhoneModel updateUserPhoneModel) {
                    if (updateUserPhoneModel.getSuccess().equalsIgnoreCase("1")) {
                        Toast.makeText(ResetPasswordFragment.this.requireActivity(), "" + updateUserPhoneModel.getMessage(), Toast.LENGTH_SHORT).show();
                        Fragment fragment = new Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putString(AppConstants.OTP, updateUserPhoneModel.getOtp());
                        fragment.setArguments(bundle);
                        Toast.makeText(ResetPasswordFragment.this.requireActivity(), "" + updateUserPhoneModel.getOtp(), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(ResetPasswordFragment.this.binding.getRoot()).navigate(R.id.updateFragment, bundle);
                        return;
                    }
                    Toast.makeText(ResetPasswordFragment.this.requireActivity(), "" + updateUserPhoneModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
