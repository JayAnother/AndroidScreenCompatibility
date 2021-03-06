package com.jay.androidscreencompatibility.ui.adaptation_density;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by Jay on 2018/11/17.
 * https://mp.weixin.qq.com/s?__biz=MzI1MzYzMjE0MQ==&mid=2247484502&idx=2&sn
 * =a60ea223de4171dd2022bc2c71e09351&chksm
 * =e9d0cfb4dea746a2e2c470448a85df0c0e7dd059099ca52a2fec0311d12b7279b3d6f1d137be&mpshare=1&scene
 * =24&srcid=1108FLwb8baf6cjj7Bdbd0GW#rd
 */
public class Density {

    private static final float DEFAULT_WIDTH_DP = 360.0f;

    private static final float DEFAULT_HEIGHT_DP = 640.0f;

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

        //获取目标density
        float targetDensity = (float) displayMetrics.widthPixels / DEFAULT_WIDTH_DP; // 根据宽适配
//        float targetDensity = (float) displayMetrics.heightPixels / DEFAULT_HEIGHT_DP;  // 根据高适配
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);
        //设置Activity的density
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

    }
}
