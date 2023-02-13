package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.databinding.ActivityLabTestBinding;

public class LabTestActivity extends AppCompatActivity {
    ActivityLabTestBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLabTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}