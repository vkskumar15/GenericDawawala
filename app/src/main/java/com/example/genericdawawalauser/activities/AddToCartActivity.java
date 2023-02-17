package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.adapters.labAdapter.AddToCartAdapter;
import com.example.genericdawawalauser.databinding.ActivityAddToCartBinding;

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

        AddToCartAdapter adapter = new AddToCartAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    private void onClicks() {

        binding.backArrowConsultPhysician.setOnClickListener(v -> {

            onBackPressed();

        });
    }
}