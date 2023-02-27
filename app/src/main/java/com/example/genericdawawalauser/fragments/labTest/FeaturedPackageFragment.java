package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPackageAdapter;
import com.example.genericdawawalauser.databinding.FragmentFeaturedPackageBinding;
import com.example.genericdawawalauser.modalClass.AddToCartPackageModal;
import com.example.genericdawawalauser.modalClass.RadiologyPackageTestModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class FeaturedPackageFragment extends Fragment {
    FragmentFeaturedPackageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeaturedPackageBinding.inflate(inflater, container, false);

        setPackageAdapter();

        return binding.getRoot();
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