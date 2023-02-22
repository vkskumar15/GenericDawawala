package com.example.genericdawawalauser.fragments.labTest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.AddToCartActivity;
import com.example.genericdawawalauser.adapters.labAdapter.LabDetailsAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabDetailsBinding;
import com.example.genericdawawalauser.modalClass.LabDetailsModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;

public class LabDetailsFragment extends Fragment {
    FragmentLabDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLabDetailsBinding.inflate(inflater, container, false);

        setAdapter();

        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();
        });

        return binding.getRoot();
    }

    private void setAdapter() {
        new ViewModalClass().labDetailsModalLiveData(requireActivity(), CommonUtils.getUserId()).observe(requireActivity(), labDetailsModal -> {
            if (labDetailsModal.getSuccess().equalsIgnoreCase("1")) {
                LabDetailsAdapter adapter = new LabDetailsAdapter(labDetailsModal.getDetails(), requireContext(), new LabDetailsAdapter.SelectLab() {
                    @Override
                    public void selectLab(LabDetailsModal.Detail detail) {

                        Fragment fragment = new Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", detail.getLabId());
                        bundle.putString("amount", detail.getActualPrice());
                        bundle.putString("discountPrice", String.valueOf(detail.getDiscountPrice()));
                        bundle.putString("dis_per", String.valueOf(detail.getDiscount()));
                        bundle.putString("total_amount", String.valueOf(detail.getTotalPrice()));
                        fragment.setArguments(bundle);

                        App.getSingleton().setLabId(detail.getLabId());

                        Navigation.findNavController(binding.getRoot()).navigate(R.id.addToCartActivity, bundle);
                    }
                });

                binding.recyclerviewCondition.setAdapter(adapter);
            }
        });
    }
}