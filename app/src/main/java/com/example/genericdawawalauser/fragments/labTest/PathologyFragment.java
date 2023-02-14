package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularCategoryAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularTestAdapter;
import com.example.genericdawawalauser.databinding.FragmentPathologyBinding;
import com.example.genericdawawalauser.modalClass.LabTestCategories;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;

public class PathologyFragment extends Fragment {

    FragmentPathologyBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPathologyBinding.inflate(inflater, container, false);


        setPopularCategoryAdapter();

        new ViewModalClass().medicineDataModalLiveData(requireActivity(), "").observe(requireActivity(), new Observer<MedicineDataModal>() {
            @Override
            public void onChanged(MedicineDataModal medicineDataModal) {
                if (medicineDataModal.getSuccess().equalsIgnoreCase("1")) {
                    LabPopularTestAdapter adapter = new LabPopularTestAdapter(medicineDataModal.getDetails(), requireContext(), new LabPopularTestAdapter.DetailsData() {
                        @Override
                        public void details(MedicineDataModal.Detail detail) {
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.pathologyDetailsFragment);
                            PathologyDetailsFragment.detail = detail;
                        }
                    }, new LabPopularTestAdapter.AddtoCart() {
                        @Override
                        public void addToCart(MedicineDataModal.Detail detail) {

                        }
                    });
                    binding.recyclerviewCondition.setAdapter(adapter);
                }
            }
        });

        onClicks();

        return binding.getRoot();

    }

    private void onClicks() {
        binding.back.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });
    }

    private void setPopularAdapter(String id) {
        new ViewModalClass().medicineDataModalLiveData(requireActivity(), id).observe(requireActivity(), new Observer<MedicineDataModal>() {
            @Override
            public void onChanged(MedicineDataModal medicineDataModal) {
                if (medicineDataModal.getSuccess().equalsIgnoreCase("1")) {
                    LabPopularTestAdapter adapter = new LabPopularTestAdapter(medicineDataModal.getDetails(), requireContext(), new LabPopularTestAdapter.DetailsData() {
                        @Override
                        public void details(MedicineDataModal.Detail detail) {
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.pathologyDetailsFragment);
                            PathologyDetailsFragment.detail = detail;
                        }
                    }, new LabPopularTestAdapter.AddtoCart() {
                        @Override
                        public void addToCart(MedicineDataModal.Detail detail) {

                        }
                    });
                    binding.recyclerviewCondition.setAdapter(adapter);
                }
            }
        });


    }

    private void setPopularCategoryAdapter() {
        new ViewModalClass().labTestCategoriesLiveData(requireActivity()).observe(requireActivity(), labTestCategories -> {
            if (labTestCategories.getSuccess().equalsIgnoreCase("1")) {
                LabPopularCategoryAdapter adapter = new LabPopularCategoryAdapter(labTestCategories.getDetails(), requireContext(), new LabPopularCategoryAdapter.ClickLab() {
                    @Override
                    public void clickLab(LabTestCategories.Detail detail) {

                        setPopularAdapter(detail.getId());

                    }
                });
                binding.recylerViewPop.setAdapter(adapter);
            }
        });
    }
}