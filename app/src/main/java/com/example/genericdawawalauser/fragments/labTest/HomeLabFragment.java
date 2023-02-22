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
import com.example.genericdawawalauser.adapters.SliderAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabCategoryAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabPackageAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularCategoryAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabPopularTestAdapter;
import com.example.genericdawawalauser.adapters.labAdapter.LabTestByConditionAdapter;
import com.example.genericdawawalauser.databinding.FragmentHomeLabBinding;
import com.example.genericdawawalauser.modalClass.LabTestCategories;
import com.example.genericdawawalauser.modalClass.MedicineDataModal;
import com.example.genericdawawalauser.modalClass.SliderData;
import com.example.genericdawawalauser.retrofit.ViewModalClass;

import java.util.ArrayList;

public class HomeLabFragment extends Fragment {
    FragmentHomeLabBinding binding;
    String url1 = "https://firebasestorage.googleapis.com/v0/b/dovio-1c283.appspot.com/o/590-banner.jpg?alt=media&token=8243b376-d9bb-47d7-80f1-42d6562a78ff";
    String url2 = "https://firebasestorage.googleapis.com/v0/b/dovio-1c283.appspot.com/o/doctor-online-on-your-laptop-online-medicine-consultation-and-diagnosis-concept-web-banner-for-medical-app-ask-doctor-online-help-and-support-2CXD1PA.jpeg?alt=media&token=2454d675-b6e2-46fc-aa85-aabd547f9a15";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/dovio-1c283.appspot.com/o/gynecologist-online-service-platform-human-anatomy-ovary-gynecologist-online-service-platform-human-anatomy-ovary-womb-197052954.jpeg?alt=media&token=f1e2b530-fcc7-44c5-912a-615ccf50d215";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

     binding  = FragmentHomeLabBinding.inflate(inflater, container, false);

     sliderImage();
     setCategoryAdapter();
     setPackageAdapter();
     setPopularCategoryAdapter();
     setTestByConditionAdapter();
     onClicks();

     try {
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
                     }, new LabPopularTestAdapter.DeletetoCart() {
                         @Override
                         public void deletetoCart(MedicineDataModal.Detail detail) {

                         }
                     });
                     binding.recylerViewPopularTest.setAdapter(adapter);
                 }
             }
         });

     } catch (Exception e) {
         throw new RuntimeException(e);


     }



     return binding.getRoot();

    }

    private void onClicks() {
        binding.radiology.setOnClickListener(v -> {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.radioLogyLabTestFragment);

        });

        binding.pathology.setOnClickListener(v -> {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.pathologyFragment);

        });
    }

    private void setTestByConditionAdapter() {
        LabTestByConditionAdapter adapter = new LabTestByConditionAdapter();
        binding.recyclerviewCondition.setAdapter(adapter);
        binding.recyclerviewOrgans.setAdapter(adapter);
        binding.recyclerviewHabits.setAdapter(adapter);
    }

    private void setPackageAdapter() {

        LabPackageAdapter adapter = new LabPackageAdapter();
        binding.packageNameRecyclerview.setAdapter(adapter);
    }

    private void setCategoryAdapter() {

        LabCategoryAdapter adapter = new LabCategoryAdapter();
        binding.recylerView.setAdapter(adapter);
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

                        }
                    }, new LabPopularTestAdapter.AddtoCart() {
                        @Override
                        public void addToCart(MedicineDataModal.Detail detail) {

                        }
                    }, new LabPopularTestAdapter.DeletetoCart() {
                        @Override
                        public void deletetoCart(MedicineDataModal.Detail detail) {

                        }
                    });
                    binding.recyclerviewCondition.setAdapter(adapter);
                }
            }
        });


    }

    private void setPopularCategoryAdapter() {
        new ViewModalClass().labTestCategoriesLiveData(requireActivity()).observe(requireActivity(), labTestCategories -> {
            if (labTestCategories.getSuccess().equalsIgnoreCase("1"))
            {
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

    private void sliderImage() {
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        sliderDataArrayList.add(new SliderData(this.
                url1));
        sliderDataArrayList.add(new SliderData(this.url2));
        sliderDataArrayList.add(new SliderData(this.url3));
        SliderAdapter adapter = new SliderAdapter(requireContext(), sliderDataArrayList);
        binding.slider.setAutoCycleDirection(0);
        binding.slider.setSliderAdapter(adapter);
        binding.slider.setScrollTimeInSec(4);
        binding.slider.setAutoCycle(true);
        binding.slider.startAutoCycle();
    }
}