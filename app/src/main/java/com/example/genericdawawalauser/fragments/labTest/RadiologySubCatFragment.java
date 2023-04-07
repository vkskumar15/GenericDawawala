package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularTestAdapter;
import com.example.genericdawawalauser.databinding.FragmentRadioSubCatBinding;
import com.example.genericdawawalauser.databinding.FragmentRadiologySubCatBinding;
import com.example.genericdawawalauser.modalClass.AddToCartPackageModal;
import com.example.genericdawawalauser.modalClass.CountCartModal;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;
import com.example.genericdawawalauser.modalClass.RadioSubCatModal;
import com.example.genericdawawalauser.modalClass.RadiologyCategoryModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

public class RadiologySubCatFragment extends Fragment {
    FragmentRadioSubCatBinding binding;
    public static RadiologyCategoryModal.Detail detail ;
    String catId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadioSubCatBinding.inflate(inflater, container, false);


        setAdapter();
       addToCartMedicine();
//        AddCartTotalItem();
        
        binding.back.setOnClickListener(v -> {
            
            requireActivity().onBackPressed();
        });

        return binding.getRoot();
    }

    private void setAdapter() {
        
        new ViewModalClass().radioSubCatModalLiveData(requireActivity(), detail.getId()).observe(requireActivity(), new Observer<MedicineDataModal>() {
            @Override
            public void onChanged(MedicineDataModal radioSubCatModal) {
                if (radioSubCatModal.getSuccess().equalsIgnoreCase("1")) {
                    LabPopularTestAdapter adapter = new LabPopularTestAdapter(radioSubCatModal.getDetails(), requireContext(), new LabPopularTestAdapter.DetailsData() {
                        @Override
                        public void details(MedicineDataModal.Detail detail) {

                        }
                    }, new LabPopularTestAdapter.AddtoCart() {
                        @Override
                        public void addToCart(MedicineDataModal.Detail detail) {

                            catId  =detail.getId();
                            addToCartMedicine();

                        }
                    }, new LabPopularTestAdapter.DeletetoCart() {
                        @Override
                        public void deletetoCart(MedicineDataModal.Detail detail) {

                        }
                    });
                    binding.recyclerviewCondition.setAdapter(adapter);


                }else {
                    Toast.makeText(requireContext(), ""+radioSubCatModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void addToCartMedicine() {
        new ViewModalClass().addToCartRadioModalLiveData(requireActivity(), CommonUtils.getUserId(), catId).observe(requireActivity(), new Observer<AddToCartPackageModal>() {
            @Override
            public void onChanged(AddToCartPackageModal addToCartPackageModal) {
                if (addToCartPackageModal.getSuccess().equalsIgnoreCase("1"))
                {
                    binding.relative.setVisibility(View.VISIBLE);

                //    AddCartTotalItem();
                    Toast.makeText(requireContext(), ""+addToCartPackageModal.getMessage(), Toast.LENGTH_SHORT).show();

                }else {

                    binding.relative.setVisibility(View.GONE);

                    Toast.makeText(requireContext(), ""+addToCartPackageModal.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void AddCartTotalItem() {
        new ViewModalClass().countCartModalLiveData(requireActivity(), CommonUtils.getUserId(),"2").observe(requireActivity(), new Observer<CountCartModal>() {
            @Override
            public void onChanged(CountCartModal countCartModal) {
                if (countCartModal.getSuccess().equalsIgnoreCase("1")) {
                    binding.relative.setVisibility(View.VISIBLE);

                    binding.itemCount.setText(String.valueOf(countCartModal.getProductCounts() + " Test"));
                } else {
                    binding.relative.setVisibility(View.GONE);
                }
            }
        });
    }
}