package com.example.genericdawawalauser.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.genericdawawalauser.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {
    public ActivityHomeBinding activityHomeBinding = null;
    public static final String data_key = "data_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());

    }


}

