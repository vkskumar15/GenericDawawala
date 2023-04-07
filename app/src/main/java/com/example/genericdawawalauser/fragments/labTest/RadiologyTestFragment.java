package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPackageAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.RadiologyCategoryAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadiologyTestBinding;
import com.example.genericdawawalauser.modalClass.AddToCartPackageModal;
import com.example.genericdawawalauser.modalClass.RadiologyCategoryModal;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class RadiologyTestFragment extends Fragment {
    FragmentRadiologyTestBinding binding;
    int price = 100, old;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRadiologyTestBinding.inflate(inflater, container, false);

        setCategoryAdapter();
        setPackageAdapter();

        binding.back.setOnClickListener(view -> {

            requireActivity().onBackPressed();

        });




        binding.viewAll.setOnClickListener(view -> {

            Navigation.findNavController(view).navigate(R.id.featuredPackageFragment);

        });


        return binding.getRoot();

    }

    private void setCategoryAdapter() {
        new ViewModalClass().radiologyCategoryModalLiveData(requireActivity()).observe(requireActivity(), new Observer<RadiologyCategoryModal>() {
            @Override
            public void onChanged(RadiologyCategoryModal radiologyCategoryModal) {
                if (radiologyCategoryModal.getSuccess().equalsIgnoreCase("1")) {
                    RadiologyCategoryAdapter adapter = new RadiologyCategoryAdapter(radiologyCategoryModal.getDetails(), requireContext(), new RadiologyCategoryAdapter.SelectCategory() {
                        @Override
                        public void category(RadiologyCategoryModal.Detail detail) {

                            RadiologySubCatFragment.detail = detail;
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.radiologySubCatFragment);
                        }
                    });
                    binding.categoryNameRecyclerview.setAdapter(adapter);
                } else {

                }
            }
        });

    }

    public void setPackageAdapter() {
        new ViewModalClass().radiologyPackageTestModalLiveData(requireActivity(), CommonUtils.getUserId()).observe(requireActivity(),
                radiologyPackageTestModal -> {
                    if (radiologyPackageTestModal.getSuccess().equalsIgnoreCase("1")) {
                        LabPackageAdapter adapter = new LabPackageAdapter(radiologyPackageTestModal.getDetails(), requireContext(), new LabPackageAdapter.SelectPackage() {
                            @Override
                            public void selectPackage(RadiologyPackageTestModal.Detail detail) {
                                RadiologyDetailsFragment.detail = detail;
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.radiologyDetailsFragment);
                            }

                            @Override
                            public void addToCart(RadiologyPackageTestModal.Detail detail) {

                                new ViewModalClass().addToCartPackageModalLiveData(requireActivity(), CommonUtils.getUserId(), detail.getId()).observe(requireActivity(), new Observer<AddToCartPackageModal>() {
                                    @Override
                                    public void onChanged(AddToCartPackageModal addToCartPackageModal) {
                                        if (addToCartPackageModal.getSuccess().equalsIgnoreCase("1")) {

                                            Toast.makeText(requireContext(), "" + addToCartPackageModal.getMessage(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(requireContext(), "" + addToCartPackageModal.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }

                            @Override
                            public void selectLab(RadiologyPackageTestModal.Detail detail) {

                                Navigation.findNavController(binding.getRoot()).navigate(R.id.labPackagesFragment);
                            }
                        });
                        binding.packageNameRecyclerview.setAdapter(adapter);
                    }
                });

    }
}