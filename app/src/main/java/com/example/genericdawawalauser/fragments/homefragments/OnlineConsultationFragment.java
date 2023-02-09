package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.FragmentOnlineConsultationBinding;


public class OnlineConsultationFragment extends Fragment {
    private FragmentOnlineConsultationBinding fragmentOnlineConsultationBinding = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentOnlineConsultationBinding = FragmentOnlineConsultationBinding.inflate(inflater, container, false);

        setActivityToolbarInvisible();
        setClicksOnViews();

        return fragmentOnlineConsultationBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setActivityToolbarInvisible();
    }

    private void setClicksOnViews() {
        fragmentOnlineConsultationBinding.proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_onlineConsultationFragment_to_proceedOnlineConsultationFragment);
            }
        });
    }

    private void setActivityToolbarInvisible() {
        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentOnlineConsultationBinding = null;
    }
}