package com.jay.androidscreencompatibility;

import android.app.Application;

/**
 * Created by Jay on 2018/9/12.
 */
public class MyApp extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application= (MyApp) getApplicationContext();
    }

    public static Application getContect() {
        return application;
    }
}
