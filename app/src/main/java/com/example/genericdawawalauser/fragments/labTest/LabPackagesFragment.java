package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPackageDetailsAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabPackagesBinding;
import com.example.genericdawawalauser.modalClass.LabPackageDetailsModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;


public class LabPackagesFragment extends Fragment {
    FragmentLabPackagesBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLabPackagesBinding.inflate(inflater, container, false);


        setAdapter();

        return binding.getRoot();
    }

    private void setAdapter() {
        new ViewModalClass().labPackageDetailsModalLiveData(requireActivity(), "1").observe(requireActivity(), new Observer<LabPackageDetailsModal>() {
            @Override
            public void onChanged(LabPackageDetailsModal labPackageDetailsModal) {
                if (labPackageDetailsModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireContext(), "" + labPackageDetailsModal.getMessage(), Toast.LENGTH_SHORT).show();

                    LabPackageDetailsAdapter adapter = new LabPackageDetailsAdapter(labPackageDetailsModal.getDetails(), requireContext(), new LabPackageDetailsAdapter.SelectLab() {
                        @Override
                        public void selectLab(LabPackageDetailsModal.Detail detail) {

                        }
                    });

                    binding.recyclerviewCondition.setAdapter(adapter);
                } else {
                    Toast.makeText(requireContext(), "" + labPackageDetailsModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}