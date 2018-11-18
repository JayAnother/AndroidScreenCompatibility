package com.jay.androidscreencompatibility.ui.adaptation_density;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by Jay on 2018/11/17.
 */
public class Density {

    private static final int DEFAULT_WIDTH = 360;

    private static float appDensity;

    private static float appScaleDensity;

    public static void setDensity(final Application application, Activity activity) {
        //获取Application的density
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
            //监听系统字体大小的变化
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //表示调整了字体大小
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources()
                            .getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        //获取目标Density
        float targetDensity = displayMetrics.widthPixels / DEFAULT_WIDTH;
        float targetScaleDensity = targetDensity + (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);
        //设置Activity的density
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

}
