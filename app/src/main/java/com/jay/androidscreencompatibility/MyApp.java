package com.jay.androidscreencompatibility;

import com.jay.androidscreencompatibility.ui.adaptation_density.DensityUtils;
import android.app.Application;

/**
 * Created by Jay on 2018/9/12.
 */
public class MyApp extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        DensityUtils.initDensity(this);
        application= (MyApp) getApplicationContext();
    }

    public static Application getContect() {
        return application;
    }
}
