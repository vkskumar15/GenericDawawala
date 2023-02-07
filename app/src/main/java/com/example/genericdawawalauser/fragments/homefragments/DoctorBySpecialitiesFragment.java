package com.example.genericdawawalauser.fragments.homefragments;

import static com.example.genericdawawalauser.activities.SplashActivity.latitude;
import static com.example.genericdawawalauser.activities.SplashActivity.longitude;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.online_consultation.DoctorBySpecialitiesAdapter;
import com.example.genericdawawalauser.databinding.FragmentDoctorBySpecialitiesBinding;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorDetailsFragment;
import com.example.genericdawawalauser.fragments.onlineConsult.DoctorTimeSlotFragment;
import com.example.genericdawawalauser.modalClass.DoctorModelDetails;
import com.example.genericdawawalauser.modalClass.DoctorModelRoot;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;

import java.util.ArrayList;
import java.util.List;

public class DoctorBySpecialitiesFragment extends Fragment {
    FragmentDoctorBySpecialitiesBinding binding;
    public static DoctorModelDetails doctorModelDetails;
    DoctorBySpecialitiesAdapter adapter;
    List<DoctorModelDetails> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDoctorBySpecialitiesBinding.inflate(inflater, container, false);


        try {
            setAdapter();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });

        searchItem();

        return binding.getRoot();
    }

    private void setAdapter() {

        new ViewModalClass().getDoctorsAsPerSpecialityLiveData(requireActivity(), doctorModelDetails.getId(), String.valueOf(latitude), String.valueOf(longitude)).observe(requireActivity(), new Observer<DoctorModelRoot>() {
            @Override
            public void onChanged(DoctorModelRoot doctorModelRoot) {
                if (doctorModelRoot.getSuccess().equalsIgnoreCase("1")) {
                    adapter = new DoctorBySpecialitiesAdapter(requireContext(), doctorModelDetails -> {

                        DoctorDetailsFragment.doctorModelDetails = doctorModelDetails;
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorDetailsFragment);

                    }, doctorModelRoot.getDetails(), doctorModelDetails -> {
                        DoctorTimeSlotFragment.doctorModelDetails = doctorModelDetails;
                        App.getSingleton().setFees(doctorModelDetails.getOnline_price());
                        App.getSingleton().setDoctor_id(doctorModelDetails.getId());
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorTimeSlotFragment);
                    });
                    binding.recyclerConsult.setAdapter(adapter);
                }
            }
        });
    }
    private void searchItem() {
        binding.searchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }
    private void filter(String text) {
        List<DoctorModelDetails> filterList = new ArrayList<>();
        for (DoctorModelDetails items : list) {
            if (items.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(items);

            }
        }
        adapter.filterList(filterList);
    }
}