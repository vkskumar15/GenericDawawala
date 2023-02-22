package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.GetUserAddressAdapter;
import com.example.genericdawawalauser.databinding.FragmentGetPatientAddressBinding;
import com.example.genericdawawalauser.modalClass.GetPatientAddress;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;


public class GetPatientAddressFragment extends Fragment {
    FragmentGetPatientAddressBinding binding;
    public static String PATIENT_ADDRESS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGetPatientAddressBinding.inflate(inflater, container, false);


        onClick();
        setAdapter();


        return binding.getRoot();

    }

    private void setAdapter() {
        new ViewModalClass().getPatientDetailsLiveData(requireActivity(), CommonUtils.getUserId()).observe(requireActivity(), new Observer<GetPatientAddress>() {
            @Override
            public void onChanged(GetPatientAddress getPatientAddress) {
                if (getPatientAddress.getSuccess().equalsIgnoreCase("1")) {
                    GetUserAddressAdapter adapter = new GetUserAddressAdapter(getPatientAddress.getDetails(), requireContext(), new GetUserAddressAdapter.SelectPatient() {
                        @Override

                        public void selectPatient(GetPatientAddress.Detail detail) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Remove Address")
                                    .setMessage("Are you sure you want to remove this item?")
                                    .setPositiveButton(android.R.string.yes, (dialog, which) ->
                                            new ViewModalClass().deleteUserAddressModalLiveData(getActivity(), detail.getId()).observe(getActivity(), removeCartModal -> {
                                                if (removeCartModal.getSuccess().equalsIgnoreCase("1")) {
                                                    Toast.makeText(getActivity(), "" + removeCartModal.getMessage(), Toast.LENGTH_SHORT).show();

                                                    setAdapter();

                                                } else {
                                                    Toast.makeText(getActivity(), "" + removeCartModal.getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            }))

                                    .setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss())
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }

                        @Override
                        public void editData(GetPatientAddress.Detail detail) {

                            AddAddressFragment.detail=detail;
                            PATIENT_ADDRESS="1";
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.addAddressFragment);
                        }

                        @Override
                        public void select(GetPatientAddress.Detail detail) {

                            App.getSingleton().setPatient_address(detail.getId());
                        }
                    });

                    binding.recyclerviewCondition.setAdapter(adapter);
                }
            }
        });
    }

    private void onClick() {

        binding.addPatient.setOnClickListener(v -> {

            Navigation.findNavController(binding.getRoot()).navigate(R.id.addAddressFragment);

        });

        binding.btnNext.setOnClickListener(v -> {

       Navigation.findNavController(binding.getRoot()).navigate(R.id.labSlotsFragment);

        });

    }
}