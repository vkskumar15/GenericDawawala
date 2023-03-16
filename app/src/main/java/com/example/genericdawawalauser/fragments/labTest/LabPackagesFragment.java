package com.example.genericdawawalauser.fragments.labTest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.LabPackageDetailsAdapter;
import com.example.genericdawawalauser.databinding.FragmentLabPackagesBinding;
import com.example.genericdawawalauser.modalClass.GetUserLabModal;
import com.example.genericdawawalauser.modalClass.LabPackageDetailsModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.App;
import com.example.genericdawawalauser.utils.CommonUtils;


public class LabPackagesFragment extends Fragment {
    FragmentLabPackagesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLabPackagesBinding.inflate(inflater, container, false);


        setAdapter();

        return binding.getRoot();
    }

    private void setAdapter() {
        new ViewModalClass().labPackageDetailsModalLiveData(requireActivity(), CommonUtils.getUserId()).observe(requireActivity(), new Observer<GetUserLabModal>() {
            @Override
            public void onChanged(GetUserLabModal labPackageDetailsModal) {
                if (labPackageDetailsModal.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(requireContext(), "" + labPackageDetailsModal.getMessage(), Toast.LENGTH_SHORT).show();
                    LabPackageDetailsAdapter adapter = new LabPackageDetailsAdapter(labPackageDetailsModal.getDetails(), requireContext(), new LabPackageDetailsAdapter.SelectLab() {
                        @Override
                        public void selectLab(GetUserLabModal.Detail detail) {
                            String labPackageStatus = "1";
                            App.getSingleton().setLabId(detail.getLabId());
                            App.getSingleton().setLab_name(detail.getName());
                            App.getSingleton().setLab_about(detail.getAbout());
                            Fragment fragment = new Fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", detail.getId());
                            bundle.putString("labPackageStatus", labPackageStatus);
                            bundle.putString("amount", detail.getActualPrice());
                            bundle.putString("discountPrice", String.valueOf(detail.getDiscountPrice()));
                            bundle.putString("dis_per", String.valueOf(detail.getDiscount()));
                            bundle.putString("total_amount", String.valueOf(detail.getTotalPrice()));
                            fragment.setArguments(bundle);
                            App.getSingleton().setHomeCollectionCheck("2");

                            Navigation.findNavController(binding.getRoot()).navigate(R.id.addPackageCartFragment, bundle);
                        }

                    });

                    binding.recyclerviewCondition.setAdapter(adapter);
                } else {
                    Toast.makeText(requireContext(), "" + labPackageDetailsModal.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}