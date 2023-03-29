package com.example.genericdawawalauser.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyShutdownReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
            // App is exiting due to shutdown
            // Put your exit detection logic here
            Log.d("asdfghjkl", "Service Destroyed");

            Toast.makeText(context, "yesss", Toast.LENGTH_SHORT).show();
        }
    }
}
