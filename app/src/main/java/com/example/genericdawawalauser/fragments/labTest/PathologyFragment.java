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
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularCategoryAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularTestAdapter;
import com.example.genericdawawalauser.databinding.FragmentPathologyBinding;
import com.example.genericdawawalauser.modalClass.AddToCartModal;
import com.example.genericdawawalauser.modalClass.LabTestCategories;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

public class PathologyFragment extends Fragment {
    FragmentPathologyBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPathologyBinding.inflate(inflater, container, false);


        setPopularCategoryAdapter();



        setPopularAdapter("");
        onClicks();

        return binding.getRoot();

    }

    private void deleteData(String id) {
        new ViewModalClass().removeToCartModalLiveData(requireActivity(), CommonUtils.getUserId(), id).observe(requireActivity(), new Observer<AddToCartModal>() {
            @Override
            public void onChanged(AddToCartModal addToCartModal) {
                if (addToCartModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "" + addToCartModal.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), "" + addToCartModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void addToCartTest(String id) {
        new ViewModalClass().addToCartModalLiveData(requireActivity(), CommonUtils.getUserId(), id).observe(requireActivity(), new Observer<AddToCartModal>() {
            @Override
            public void onChanged(AddToCartModal addToCartModal) {
                if (addToCartModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "" + addToCartModal.getMessage(), Toast.LENGTH_SHORT).show();
                    setPopularAdapter("");
                } else {
                    Toast.makeText(requireActivity(), "" + addToCartModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
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

                            addToCartTest(detail.getId());
                        }
                    }, new LabPopularTestAdapter.DeletetoCart() {
                        @Override
                        public void deletetoCart(MedicineDataModal.Detail detail) {
                            deleteData(detail.getId());
                            setPopularAdapter("");
                        }
                    });
                    binding.recyclerviewCondition.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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
                adapter.notifyDataSetChanged();
            }
        });
    }
}