package com.example.genericdawawalauser.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class SharedPre {
    private final Context context;
    SharedPreferences.Editor prefsEditor;
    SharedPreferences sharedpreferences;

    public SharedPre(Context context2) {
        this.context = context2;
        SharedPreferences sharedPreferences = context2.getSharedPreferences("Sepik", 0);
        this.sharedpreferences = sharedPreferences;
        this.prefsEditor = sharedPreferences.edit();
    }

    public <T> T getModel(String key, Class<T> type) {
        return new Gson().fromJson(this.sharedpreferences.getString(key, ""), type);
    }

    public void saveModel(String key, Object modelClass) {
        this.prefsEditor.putString(key, new Gson().toJson(modelClass));
        this.prefsEditor.apply();
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return this.sharedpreferences.getString(key, "");
    }

    public void clearPreferences() {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}
