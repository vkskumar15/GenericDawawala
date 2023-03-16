package com.example.genericdawawalauser.utils;

import android.app.Application;
import android.content.Context;

public class
App extends Application {
    private static Context context;
    private static SharedPre sharedPre;
    private static SingleModel singleTonModel;
    private static Singleton singleton;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        singleton = new Singleton();
        sharedPre = new SharedPre(context);
        singleTonModel = new SingleModel();
    }

    public static SharedPre getSharedPre() {
        return sharedPre;
    }



    public static Singleton getSingleton() {
        return singleton;
    }
}
