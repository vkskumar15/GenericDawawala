package com.example.genericdawawalauser.fragments.homefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.SearchDoctorAdapter;
import com.example.genericdawawalauser.databinding.FragmentSearchADoctorBinding;

public class SearchADoctorFragment extends Fragment {

   FragmentSearchADoctorBinding binding ;

    public SearchADoctorFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =binding.inflate(inflater, container, false);
        setAdapter();


        return  binding.getRoot();
    }

    private void setAdapter() {

        binding.rvCommonSearches.setAdapter( new SearchDoctorAdapter(requireContext(), new SearchDoctorAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClick(int pos) {

                Navigation.findNavController(getView()).navigate(R.id.action_searchADoctorFragment_to_doctorBySpecialitiesFragment);
            }
        }));

    }

    @Override
    public void onResume() {
        super.onResume();

        if (isAdded() && getActivity() != null) {
            getActivity().findViewById(R.id.appBar).setVisibility(View.GONE);
        }
    }
}