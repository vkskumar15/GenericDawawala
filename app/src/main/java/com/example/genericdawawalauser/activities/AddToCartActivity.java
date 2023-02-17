package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.AddToCartAdapter;
import com.example.genericdawawalauser.databinding.ActivityAddToCartBinding;
import com.example.genericdawawalauser.modalClass.AddCartLabModal;
import com.example.genericdawawalauser.retrofit.ViewModalClass;
import com.example.genericdawawalauser.utils.CommonUtils;

public class AddToCartActivity extends AppCompatActivity {
    ActivityAddToCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddToCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClicks();
       setAdapter();

    }

    private void setAdapter() {

        new ViewModalClass().addCartLabModalLiveData(AddToCartActivity.this, CommonUtils.getUserId(), "2").observe(AddToCartActivity.this, new Observer<AddCartLabModal>() {
            @Override
            public void onChanged(AddCartLabModal addCartLabModal) {
                if (addCartLabModal.getSuccess().equalsIgnoreCase("1"))
                {
                    binding.test.setText("Pathology test "+addCartLabModal.getDetails().size());
                    AddToCartAdapter adapter = new AddToCartAdapter(addCartLabModal.getDetails(), AddToCartActivity.this, new AddToCartAdapter.TotalDiscount() {
                        @Override
                        public void discount(AddCartLabModal.Detail detail) {

                        }
                    });
                    binding.recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(AddToCartActivity.this, ""+addCartLabModal.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void onClicks() {

        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            onBackPressed();

        });
    }
}