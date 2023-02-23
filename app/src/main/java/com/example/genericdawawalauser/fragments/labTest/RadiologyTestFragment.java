package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.genericdawawalauser.adapters.labAdapter.LabPackageAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.RadiologyCategoryAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadiologyTestBinding;
import com.example.genericdawawalauser.modalClass.RadiologyCategoryModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;


public class RadiologyTestFragment extends Fragment {
    FragmentRadiologyTestBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     binding = FragmentRadiologyTestBinding.inflate(inflater, container, false);

     setCategoryAdapter();
     setPackageAdapter();

     binding.back.setOnClickListener(view -> {

         requireActivity().onBackPressed();

     });

     return binding.getRoot();

    }

    private void setCategoryAdapter() {
        new ViewModalClass().radiologyCategoryModalLiveData(requireActivity()).observe(requireActivity(), new Observer<RadiologyCategoryModal>() {
            @Override
            public void onChanged(RadiologyCategoryModal radiologyCategoryModal) {
                if (radiologyCategoryModal.getSuccess().equalsIgnoreCase("1"))
                {
                    RadiologyCategoryAdapter adapter = new RadiologyCategoryAdapter(radiologyCategoryModal.getDetails(), requireContext(), new RadiologyCategoryAdapter.SelectCategory() {
                        @Override
                        public void category(RadiologyCategoryModal.Detail detail) {

                        }
                    });
                    binding.categoryNameRecyclerview.setAdapter(adapter);
                }else {

                }
            }
        });

    }

    private void setPackageAdapter() {

        LabPackageAdapter adapter = new LabPackageAdapter();
        binding.packageNameRecyclerview.setAdapter(adapter);
    }
}