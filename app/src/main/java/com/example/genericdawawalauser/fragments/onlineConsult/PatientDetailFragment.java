package com.example.genericdawawalauser.fragments.onlineConsult;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.RelationAdapter;
import com.example.genericdawawalauser.databinding.FragmentPatientDetailBinding;
import com.example.genericdawawalauser.modalClass.RelationModal;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientDetailFragment extends Fragment {
    private RelationAdapter adapter;
    FragmentPatientDetailBinding binding;
    private List<RelationModal> list = new ArrayList();
    String gender, relation;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentPatientDetailBinding.inflate(inflater, container, false);

        setAdapter();
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


        binding.number.setText(App.getSharedPre().getString(AppConstants.PHONE_NUMBER));
        binding.name.setText(App.getSharedPre().getString(AppConstants.USER_NAME));

        return this.binding.getRoot();
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


        this.binding.btnNext.setOnClickListener(view -> {
            String age = binding.age.getText().toString();
            String number = binding.number.getText().toString();
            String name = binding.name.getText().toString();

            if (relation==null)
            {
                Toast.makeText(requireActivity(), "Select Relation", Toast.LENGTH_SHORT).show();

            }else if (age.isEmpty())
            {
                Toast.makeText(requireActivity(), "Enter age", Toast.LENGTH_SHORT).show();

            }else if (gender==null)
            {
                Toast.makeText(requireActivity(), "Select Gender", Toast.LENGTH_SHORT).show();

            }else if (number.isEmpty())
            {
                Toast.makeText(requireActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();

            }else if (name.isEmpty())
            {
                Toast.makeText(requireActivity(), "Enter Patient Name", Toast.LENGTH_SHORT).show();

            }else {

                App.getSingleton().setRelation(relation);
                App.getSingleton().setAge(age);
                App.getSingleton().setGender(gender);
                App.getSingleton().setNumber(number);
                App.getSingleton().setName(name);
                App.getSingleton().setAppointmentStatus("1");

                Navigation.findNavController(this.binding.getRoot()).navigate(R.id.healthProblemOnlineFragment);

            }

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
