package com.example.genericdawawalauser.fragments.profiles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentDoctorProfileBinding;


public class DoctorProfileFragment extends Fragment {
  FragmentDoctorProfileBinding binding ;




    public DoctorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        onClick();

        return binding.getRoot();
    }

    private void onClick() {
      binding.offlineModeConstraintlayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Navigation.findNavController(v).navigate(R.id.action_doctorProfileFragment_to_selectSlotFragment);

          }
      });
        binding.onlineModeConstraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_doctorProfileFragment_to_selectSlotFragment);

            }
        });
    }
}