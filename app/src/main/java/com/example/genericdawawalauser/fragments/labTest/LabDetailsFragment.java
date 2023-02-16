package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabDetailsAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabDetailsBinding;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;


public class LabDetailsFragment extends Fragment {
    FragmentLabDetailsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentLabDetailsBinding.inflate(inflater, container, false);


       setAdapter();

       binding.backArrowConsultPhysician.setOnClickListener(v -> {
           requireActivity().onBackPressed();
       });

       return binding.getRoot();
    }

    private void setAdapter() {
        new ViewModalClass().labDetailsModalLiveData(requireActivity()).observe(requireActivity(), new Observer<LabDetailsModal>() {
            @Override
            public void onChanged(LabDetailsModal labDetailsModal) {
                if (labDetailsModal.getSuccess().equalsIgnoreCase("1"))
                {
                    LabDetailsAdapter adapter = new LabDetailsAdapter(labDetailsModal.getDetails(), requireContext(), new LabDetailsAdapter.SelectLab() {
                        @Override
                        public void selectLab(LabDetailsModal.Detail detail) {

                            PathologyFragment.detail = detail;
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.pathologyFragment);
                        }
                    });

                    binding.recyclerviewCondition.setAdapter(adapter);
                }
            }
        });
    }
}