package com.example.genericdawawalauser.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommonUtils {
    public static AlertDialog alertDialog;
    private static ProgressDialog progressDialog;
    Context context;

    public static void hideDoctorBottomNavigation(boolean b, View view) {
        if (b) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static boolean isNetworkConnected(Context context2) {
        return ((ConnectivityManager) context2.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static void showProgress(Activity activity, String message) {
        ProgressDialog progressDialog2 = new ProgressDialog(activity);
        progressDialog = progressDialog2;
        progressDialog2.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void dismissProgress() {
        ProgressDialog progressDialog2 = progressDialog;
        if (progressDialog2 != null && progressDialog2.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static RequestBody stringToRequestBody(String s) {
        return RequestBody.create(MediaType.parse("text/plain"), s);
    }

    public static MultipartBody.Part imageToMultiPart(String parameter, String imagePath) {
        try {
            if (imagePath.equalsIgnoreCase("")) {
                return MultipartBody.Part.createFormData(parameter, "", RequestBody.create(MediaType.parse("multipart/form-data"), ""));
            }
            File file = new File(imagePath);
            return MultipartBody.Part.createFormData(parameter, file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        } catch (NullPointerException exception) {
            Log.d("ContentValues", "onChanged: " + exception);
            return null;
        }
    }

    public static String getUserId() {
        return App.getSharedPre().getString(AppConstants.USER_ID);
    }
}
