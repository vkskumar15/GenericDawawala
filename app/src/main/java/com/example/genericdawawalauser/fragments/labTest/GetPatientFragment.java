package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
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
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class GetPatientFragment extends Fragment {
    FragmentGetPatitentBinding binding;
    String checkID, uncheckID;
    private List<String> catget = new ArrayList<>();

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

        binding.btnNext.setOnClickListener(view -> {
            if (checkID!=null){
                getIds();
                App.getSingleton().setPatient_details(checkID);
                App.getSingleton().setTotal_patient(String.valueOf(catget.size()));

                Navigation.findNavController(view).navigate(R.id.getPatientAddressFragment);
            }else {
                Toast.makeText(requireActivity(), "Please Select Patient", Toast.LENGTH_SHORT).show();
            }

        });

        binding.back.setOnClickListener(view -> {

            requireActivity().onBackPressed();
        });
    }

    private void setAdapter() {
        new ViewModalClass().getFamilyMemberModalLiveData(requireActivity(), CommonUtils.getUserId()).observe(requireActivity(), getFamilyMemberModal -> {
            if (getFamilyMemberModal.getSuccess().equalsIgnoreCase("1")) {
                Toast.makeText(requireActivity(), "" + getFamilyMemberModal.getMessage(), Toast.LENGTH_SHORT).show();
                FamilyMemberAdapter addPatientsAdapter = new FamilyMemberAdapter(getFamilyMemberModal.getDetails(), requireActivity(), new FamilyMemberAdapter.SelectPatient() {
                    @Override
                    public void selectPatient(GetFamilyMemberModal.Detail detail) {

                        new AlertDialog.Builder(getActivity())
                                .setTitle("Remove Details")
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

                    @Override
                    public void onCheck(String id) {

                        catget.add(id);

                        getIds();

                    }

                    @Override
                    public void onUnCheck(String id) {
                        catget.remove(id);

                        getIds();
                    }
                });
                binding.recyclerviewCondition.setAdapter(addPatientsAdapter);
            } else {
                Toast.makeText(requireActivity(), "" + getFamilyMemberModal.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getIds() {
        if (catget.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : catget) {
                sb.append(s).append(",");
            }
            checkID = sb.deleteCharAt(sb.length() - 1).toString();
        }
    }
}