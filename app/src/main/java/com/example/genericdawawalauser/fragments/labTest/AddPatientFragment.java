package com.example.genericdawawalauser.fragments.labTest;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.RelationAdapter;
import com.example.genericdawawalauser.databinding.FragmentAddPatientBinding;
import com.example.genericdawawalauser.modalClass.AddFamilyMember;
import com.example.genericdawawalauser.modalClass.RelationModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;
import com.example.genericdawawalauser.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class AddPatientFragment extends Fragment {
    FragmentAddPatientBinding binding;
    private RelationAdapter adapter;
    private List<RelationModal> list = new ArrayList();
    String gender, relation;

    String id, status, age, number, name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddPatientBinding.inflate(inflater, container, false);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
            status = bundle.getString("status");
            age = bundle.getString("age");
            number = bundle.getString("number");
            name = bundle.getString("name");
        }


        onClicks();

        this.list.add(new RelationModal("Myself"));
        this.list.add(new RelationModal("Wife"));
        this.list.add(new RelationModal("Father"));
        this.list.add(new RelationModal("Mother"));
        this.list.add(new RelationModal("Friend"));
        this.list.add(new RelationModal("Husband"));
        this.list.add(new RelationModal("Partner"));
        this.list.add(new RelationModal("Brother"));
        this.list.add(new RelationModal("Sister"));
        this.list.add(new RelationModal("Daughter"));
        this.list.add(new RelationModal("Son"));
        this.list.add(new RelationModal("Other"));


        setAdapter();

        if (status == "1") {
            editData();


            binding.age.setText(age);
            binding.number.setText(number);
            binding.name.setText(name);
        } else {
            binding.number.setText(App.getSharedPre().getString(AppConstants.PHONE_NUMBER));
            binding.name.setText(App.getSharedPre().getString(AppConstants.USER_NAME));

            addDataPatientDetails();
        }


        return this.binding.getRoot();
    }

    private void editData() {
        binding.btnNext.setOnClickListener(v -> {
            String age = binding.age.getText().toString();
            String number = binding.number.getText().toString();
            String name = binding.name.getText().toString();

            new ViewModalClass().editFamilyMemberLiveData(requireActivity(), id, CommonUtils.getUserId(), name, number, gender, age, relation).observe(requireActivity(), new Observer<AddFamilyMember>() {
                @Override
                public void onChanged(AddFamilyMember addFamilyMember) {
                    if (addFamilyMember.getSuccess().equalsIgnoreCase("1")) {
                        requireActivity().onBackPressed();
                        Toast.makeText(requireActivity(), "" + addFamilyMember.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity(), "" + addFamilyMember.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        });


    }

    private void addDataPatientDetails() {
        this.binding.btnNext.setOnClickListener(view -> {
            String age = binding.age.getText().toString();
            String number = binding.number.getText().toString();
            String name = binding.name.getText().toString();

            if (relation == null) {
                Toast.makeText(requireActivity(), "Select Relation", Toast.LENGTH_SHORT).show();

            } else if (age.isEmpty()) {
                Toast.makeText(requireActivity(), "Enter age", Toast.LENGTH_SHORT).show();

            } else if (gender == null) {
                Toast.makeText(requireActivity(), "Select Gender", Toast.LENGTH_SHORT).show();

            } else if (number.isEmpty()) {
                Toast.makeText(requireActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();

            } else if (name.isEmpty()) {
                Toast.makeText(requireActivity(), "Enter Patient Name", Toast.LENGTH_SHORT).show();

            } else {

                App.getSingleton().setRelation(relation);
                App.getSingleton().setAge(age);
                App.getSingleton().setGender(gender);
                App.getSingleton().setNumber(number);
                App.getSingleton().setName(name);
                App.getSingleton().setAppointmentStatus("1");

                new ViewModalClass().addFamilyMemberLiveData(requireActivity(), CommonUtils.getUserId(), name, number, gender, age, relation).observe(requireActivity(), new Observer<AddFamilyMember>() {
                    @Override
                    public void onChanged(AddFamilyMember addFamilyMember) {
                        if (addFamilyMember.getSuccess().equalsIgnoreCase("1")) {
                            requireActivity().onBackPressed();

                            Toast.makeText(requireActivity(), "" + addFamilyMember.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireActivity(), "" + addFamilyMember.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

        });
    }


    private void onClicks() {

        binding.male.setOnClickListener(v -> {

            gender = "male";
            binding.male.setBackgroundColor(Color.parseColor("#0daaed"));
            binding.feMale.setBackgroundColor(Color.parseColor("#FFFFFFFF"));


        });

        binding.feMale.setOnClickListener(v -> {

            gender = "female";
            binding.feMale.setBackgroundColor(Color.parseColor("#0daaed"));
            binding.male.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        });


        this.binding.back.setOnClickListener(view -> {
            requireActivity().onBackPressed();

        });
    }


    private void setAdapter() {
        this.adapter = new RelationAdapter(requireContext(), this.list, relationModal -> {
            relation = relationModal.getName();
        });
        this.binding.recylerView.setAdapter(this.adapter);
    }

}