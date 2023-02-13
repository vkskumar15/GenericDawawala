package com.example.genericdawawalauser.fragments.drAppointment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentLocationBinding;
import com.example.genericdawawalauser.fragments.homefragments.DoctorAppointmentBySpecialitiesFragment;


public class LocationFragment extends Fragment {
    FragmentLocationBinding binding;
    public static String specialityId, specialityStatus, locality, clinic_name;
    String type, gender, language, price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);

        onClick();

        return binding.getRoot();
    }

    private void onClick() {
        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });

        binding.btnApply.setOnClickListener(v -> {

            locality = binding.locality.getText().toString();
            clinic_name = binding.clinicName.getText().toString();

            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("locality", locality);
            bundle.putString("clinic_name", clinic_name);
            bundle.putInt("specialityStatus", 12);
            fragment.setArguments(bundle);

            Navigation.findNavController(binding.getRoot()).navigate(R.id.doctorAppointmentBySpecialitiesFragment, bundle);

        });

        binding.txtClear.setOnClickListener(v -> {

            Dialog warning_box = new Dialog(getContext());
            warning_box.setContentView(R.layout.delete_prescription_dialogue_box);
            warning_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = warning_box.getWindow();
            warning_box.setCanceledOnTouchOutside(false);
            window.setGravity(Gravity.CENTER);
            warning_box.show();

            warning_box.findViewById(R.id.yes_btn).setOnClickListener(view2 -> {

                removeFilterFromDesign();

                warning_box.dismiss();

            });

            warning_box.findViewById(R.id.no_btn).setOnClickListener(view2 -> {

                warning_box.dismiss();

            });

        });

        binding.relativeUnder500.setOnClickListener(v -> {

            price = "1";

            binding.txtUnder500.setTextColor(getResources().getColor(R.color.white));
            binding.relativeUnder500.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtAbove500.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relative500ormore.setBackgroundResource(R.drawable.bg_filters_un_selected);

            DoctorAppointmentBySpecialitiesFragment.priceCheck = 1;

        });
        binding.relative500ormore.setOnClickListener(v -> {

            price = "2";

            binding.txtAbove500.setTextColor(getResources().getColor(R.color.white));
            binding.relative500ormore.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtUnder500.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeUnder500.setBackgroundResource(R.drawable.bg_filters_un_selected);

            DoctorAppointmentBySpecialitiesFragment.priceCheck = 2;

        });
        binding.relativeEnglish.setOnClickListener(v -> {
            language = "english";

            binding.txtEnglish.setTextColor(getResources().getColor(R.color.white));
            binding.relativeEnglish.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtHindi.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeHindi.setBackgroundResource(R.drawable.bg_filters_un_selected);

        });
        binding.relativeHindi.setOnClickListener(v -> {

            language = "hindi";

            binding.txtHindi.setTextColor(getResources().getColor(R.color.white));
            binding.relativeHindi.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtEnglish.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeEnglish.setBackgroundResource(R.drawable.bg_filters_un_selected);

        });
        binding.relativeMale.setOnClickListener(v -> {

            gender = "male";

            binding.txtMale.setTextColor(getResources().getColor(R.color.white));
            binding.relativeMale.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtFemale.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeFemale.setBackgroundResource(R.drawable.bg_filters_un_selected);

            DoctorAppointmentBySpecialitiesFragment.genderCheck = 1;

        });
        binding.relativeFemale.setOnClickListener(v -> {
            gender = "female";

            binding.txtFemale.setTextColor(getResources().getColor(R.color.white));
            binding.relativeFemale.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtMale.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeMale.setBackgroundResource(R.drawable.bg_filters_un_selected);

            DoctorAppointmentBySpecialitiesFragment.genderCheck = 2;

        });
        binding.relativeFilterOnline.setOnClickListener(v -> {
            binding.txtOnline.setTextColor(getResources().getColor(R.color.white));
            binding.relativeFilterOnline.setBackgroundResource(R.drawable.bg_filters_selected);
            binding.videoFilter.setColorFilter(getResources().getColor(R.color.white));

            binding.txtOffline.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeFilterInPerson.setBackgroundResource(R.drawable.bg_filters_un_selected);
            binding.inPersonFilter.setColorFilter(getResources().getColor(R.color.appcolor));
            type = "online";


            DoctorAppointmentBySpecialitiesFragment.consultationType = 1;

        });
        binding.relativeFilterInPerson.setOnClickListener(v -> {

            type = "offline";
            binding.txtOnline.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeFilterOnline.setBackgroundResource(R.drawable.bg_filters_un_selected);
            binding.videoFilter.setColorFilter(getResources().getColor(R.color.appcolor));

            binding.txtOffline.setTextColor(getResources().getColor(R.color.white));
            binding.relativeFilterInPerson.setBackgroundResource(R.drawable.bg_filters_selected);
            binding.inPersonFilter.setColorFilter(getResources().getColor(R.color.white));
            DoctorAppointmentBySpecialitiesFragment.consultationType = 2;

        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (DoctorAppointmentBySpecialitiesFragment.priceCheck == 1) {

            binding.txtUnder500.setTextColor(getResources().getColor(R.color.white));
            binding.relativeUnder500.setBackgroundResource(R.drawable.bg_filters_selected);
            binding.txtAbove500.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relative500ormore.setBackgroundResource(R.drawable.bg_filters_un_selected);

        } else if (DoctorAppointmentBySpecialitiesFragment.priceCheck == 2) {

            binding.txtAbove500.setTextColor(getResources().getColor(R.color.white));
            binding.relative500ormore.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtUnder500.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeUnder500.setBackgroundResource(R.drawable.bg_filters_un_selected);

        }

        if (DoctorAppointmentBySpecialitiesFragment.genderCheck == 1) {

            binding.txtMale.setTextColor(getResources().getColor(R.color.white));
            binding.relativeMale.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtFemale.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeFemale.setBackgroundResource(R.drawable.bg_filters_un_selected);

        } else if (DoctorAppointmentBySpecialitiesFragment.genderCheck == 2) {

            binding.txtFemale.setTextColor(getResources().getColor(R.color.white));
            binding.relativeFemale.setBackgroundResource(R.drawable.bg_filters_selected);

            binding.txtMale.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeMale.setBackgroundResource(R.drawable.bg_filters_un_selected);

        }

        if (DoctorAppointmentBySpecialitiesFragment.consultationType == 1) {

            binding.txtOnline.setTextColor(getResources().getColor(R.color.white));
            binding.relativeFilterOnline.setBackgroundResource(R.drawable.bg_filters_selected);
            binding.videoFilter.setColorFilter(getResources().getColor(R.color.white));

            binding.txtOffline.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeFilterInPerson.setBackgroundResource(R.drawable.bg_filters_un_selected);
            binding.inPersonFilter.setColorFilter(getResources().getColor(R.color.appcolor));

        } else if (DoctorAppointmentBySpecialitiesFragment.consultationType == 2) {

            binding.txtOnline.setTextColor(getResources().getColor(R.color.appcolor));
            binding.relativeFilterOnline.setBackgroundResource(R.drawable.bg_filters_un_selected);
            binding.videoFilter.setColorFilter(getResources().getColor(R.color.appcolor));

            binding.txtOffline.setTextColor(getResources().getColor(R.color.white));
            binding.relativeFilterInPerson.setBackgroundResource(R.drawable.bg_filters_selected);
            binding.inPersonFilter.setColorFilter(getResources().getColor(R.color.white));
        }
    }

    private void removeFilterFromDesign() {

        binding.txtUnder500.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeUnder500.setBackgroundResource(R.drawable.bg_filters_un_selected);

        binding.txtAbove500.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relative500ormore.setBackgroundResource(R.drawable.bg_filters_un_selected);

        binding.txtEnglish.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeEnglish.setBackgroundResource(R.drawable.bg_filters_un_selected);

        binding.txtHindi.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeHindi.setBackgroundResource(R.drawable.bg_filters_un_selected);

        binding.txtMale.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeMale.setBackgroundResource(R.drawable.bg_filters_un_selected);

        binding.txtFemale.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeFemale.setBackgroundResource(R.drawable.bg_filters_un_selected);

        binding.txtOnline.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeFilterOnline.setBackgroundResource(R.drawable.bg_filters_un_selected);
        binding.videoFilter.setColorFilter(getResources().getColor(R.color.appcolor));

        binding.txtOffline.setTextColor(getResources().getColor(R.color.appcolor));
        binding.relativeFilterInPerson.setBackgroundResource(R.drawable.bg_filters_un_selected);
        binding.inPersonFilter.setColorFilter(getResources().getColor(R.color.appcolor));

        DoctorAppointmentBySpecialitiesFragment.priceCheck = 0;
        DoctorAppointmentBySpecialitiesFragment.genderCheck = 0;
        DoctorAppointmentBySpecialitiesFragment.consultationType = 0;

    }
}