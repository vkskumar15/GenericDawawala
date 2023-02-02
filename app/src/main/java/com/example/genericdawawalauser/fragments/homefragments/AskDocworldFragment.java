package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;


public class AskDocworldFragment extends Fragment {


    public AskDocworldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask_docworld, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();

        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }
}