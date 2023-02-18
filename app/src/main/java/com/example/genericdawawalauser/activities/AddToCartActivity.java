package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.AddToCartAdapter;
import com.example.genericdawawalauser.databinding.ActivityAddToCartBinding;
import com.example.genericdawawalauser.databinding.FragmentLabDetailsBinding;
import com.example.genericdawawalauser.modalClass.AddCartLabModal;
import com.example.genericdawawalauser.modalClass.RemoveCartModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

public class AddToCartActivity extends Fragment {
    ActivityAddToCartBinding binding;
    String id, amount, discountPrice, dis_per, total_amount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityAddToCartBinding.inflate(inflater, container, false);


        onClicks();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
            amount = bundle.getString("amount");
            discountPrice = bundle.getString("discountPrice");
            dis_per = bundle.getString("dis_per");
            total_amount = bundle.getString("total_amount");
        }

        setDetails();
        setAdapter();

        return binding.getRoot();

    }

    private void setDetails() {

        binding.totalAmount.setText(total_amount);
        binding.totalPaid.setText(total_amount);
    }

    private void setAdapter() {
        new ViewModalClass().addCartLabModalLiveData(getActivity(), CommonUtils.getUserId(), "2").observe(getActivity(), new Observer<AddCartLabModal>() {
            @Override
            public void onChanged(AddCartLabModal addCartLabModal) {
                if (addCartLabModal.getSuccess().equalsIgnoreCase("1")) {
                    binding.test.setText("Pathology test: " + addCartLabModal.getDetails().size());
                    AddToCartAdapter adapter = new AddToCartAdapter(addCartLabModal.getDetails(), getActivity(), new AddToCartAdapter.TotalDiscount() {
                        @Override
                        public void discount(AddCartLabModal.Detail detail) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Remove Cart")
                                    .setMessage("Are you sure you want to remove this item?")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            new ViewModalClass().removeCartModalLiveData(getActivity(), CommonUtils.getUserId(), id).observe(getActivity(), new Observer<RemoveCartModal>() {
                                                @Override
                                                public void onChanged(RemoveCartModal removeCartModal) {
                                                    if (removeCartModal.getSuccess().equalsIgnoreCase("1")) {
                                                        Toast.makeText(getActivity(), "" + removeCartModal.getDetails(), Toast.LENGTH_SHORT).show();

                                                        setAdapter();

                                                    } else {
                                                        Toast.makeText(getActivity(), "" + removeCartModal.getDetails(), Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });

                                        }
                                    })

                                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    });
                    binding.recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "" + addCartLabModal.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void onClicks() {

        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            requireActivity().onBackPressed();

        });
    }
}