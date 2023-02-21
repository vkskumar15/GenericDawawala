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
import com.example.genericdawawalauser.adapters.labAdapter.FamilyMemberAdapter;
import com.example.genericdawawalauser.databinding.FragmentGetPatitentBinding;
import com.example.genericdawawalauser.modalClass.GetFamilyMemberModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;


public class GetPatientFragment extends Fragment {
    FragmentGetPatitentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGetPatitentBinding.inflate(inflater, container, false);


        setAdapter();
        onClicks();

        return binding.getRoot();

    }

    private void onClicks() {
        binding.addPatient.setOnClickListener(view -> {

            Navigation.findNavController(view).navigate(R.id.addPatientFragment);
        });

        binding.back.setOnClickListener(view -> {

            requireActivity().onBackPressed();
        });
    }

    private void setAdapter() {
        new ViewModalClass().getFamilyMemberModalLiveData(requireActivity(), CommonUtils.getUserId()).observe(requireActivity(), new Observer<GetFamilyMemberModal>() {
            @Override
            public void onChanged(GetFamilyMemberModal getFamilyMemberModal) {

                if (getFamilyMemberModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireActivity(), "" + getFamilyMemberModal.getMessage(), Toast.LENGTH_SHORT).show();
                    FamilyMemberAdapter addPatientsAdapter = new FamilyMemberAdapter(getFamilyMemberModal.getDetails(), requireActivity(), new FamilyMemberAdapter.SelectPatient() {
                        @Override
                        public void selectPatient(GetFamilyMemberModal.Detail detail) {

                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Remove Cart")
                                    .setMessage("Are you sure you want to remove this item?")
                                    .setPositiveButton(android.R.string.yes, (dialog, which) ->
                                            new ViewModalClass().deleteFamilyMemberModalLiveData(getActivity(), detail.getId()).observe(getActivity(), removeCartModal -> {
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
                        public void editData(GetFamilyMemberModal.Detail detail) {

                            Fragment fragment = new Fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", detail.getId());
                            bundle.putString("age", detail.getAge());
                            bundle.putString("number", detail.getPhone());
                            bundle.putString("name", detail.getName());
                            bundle.putString("status", "1");
                            fragment.setArguments(bundle);

                            Navigation.findNavController(binding.getRoot()).navigate(R.id.addPatientFragment, bundle);
                        }
                    });
                    binding.recyclerviewCondition.setAdapter(addPatientsAdapter);
                } else {
                    Toast.makeText(requireActivity(), "" + getFamilyMemberModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}