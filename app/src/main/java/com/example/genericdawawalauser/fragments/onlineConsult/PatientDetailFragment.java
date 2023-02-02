package com.example.genericdawawalauser.fragments.onlineConsult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.RelationAdapter;
import com.example.genericdawawalauser.databinding.FragmentPatientDetailBinding;
import com.example.genericdawawalauser.modalClass.RelationModal;
import java.util.ArrayList;
import java.util.List;

public class PatientDetailFragment extends Fragment {
    private RelationAdapter adapter;
    FragmentPatientDetailBinding binding;
    private List<RelationModal> list = new ArrayList();

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
        return this.binding.getRoot();
    }

    private void onClicks() {
        this.binding.btnNext.setOnClickListener(view -> {
            Navigation.findNavController(this.binding.getRoot()).navigate(R.id.homeConsultationFragment);

        });
        this.binding.back.setOnClickListener(view -> {
            requireActivity().onBackPressed();

        });
    }


    private void setAdapter() {
        this.adapter = new RelationAdapter(requireContext(), this.list);
        this.binding.recylerView.setAdapter(this.adapter);
    }
}
