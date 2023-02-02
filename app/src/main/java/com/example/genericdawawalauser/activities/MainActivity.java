package com.example.genericdawawalauser.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genericdawawalauser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());


    }


}