package com.example.genericdawawalauser.utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;

import com.example.genericdawawalauser.R;

public class NeTWorkChange extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
        if (!Common.isConnectedToInternet(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet, (ViewGroup) null);
            builder.setView(layout_dialog);
            final AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
            dialog.getWindow().setGravity(17);
            ((AppCompatButton) layout_dialog.findViewById(R.id.btnRetry)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                    NeTWorkChange.this.onReceive(context, intent);
                }
            });
        }
    }
}
