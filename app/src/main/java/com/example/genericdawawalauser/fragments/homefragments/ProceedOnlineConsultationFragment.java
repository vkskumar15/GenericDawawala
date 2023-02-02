package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentProceedOnlineConsultationBinding;

public class ProceedOnlineConsultationFragment extends Fragment {
    private FragmentProceedOnlineConsultationBinding fragmentProceedOnlineConsultationBinding = null;

    public ProceedOnlineConsultationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProceedOnlineConsultationBinding = FragmentProceedOnlineConsultationBinding.inflate(inflater, container, false);
        return fragmentProceedOnlineConsultationBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setActivityToolbarInvisible();
    }

    private void setActivityToolbarInvisible() {
        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }
}