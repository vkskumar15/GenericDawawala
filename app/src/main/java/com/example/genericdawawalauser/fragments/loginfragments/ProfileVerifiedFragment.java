package com.example.genericdawawalauser.fragments.loginfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentProfileVerifiedBinding;


public class ProfileVerifiedFragment extends Fragment {
    private FragmentProfileVerifiedBinding fragmentProfileVerifiedBinding = null;


    public ProfileVerifiedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileVerifiedBinding = FragmentProfileVerifiedBinding.inflate(inflater, container, false);
        setClicksOnViews();
        return fragmentProfileVerifiedBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentProfileVerifiedBinding = null;
    }

    private void setClicksOnViews() {
        fragmentProfileVerifiedBinding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_profileVerifiedFragment_to_homeActivity);

            }
        });
    }
}