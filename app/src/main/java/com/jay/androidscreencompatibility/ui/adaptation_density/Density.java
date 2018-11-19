package com.jay.androidscreencompatibility.ui.adaptation_density;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by Jay on 2018/11/17.
 * https://mp.weixin.qq.com/s?__biz=MzI1MzYzMjE0MQ==&mid=2247484502&idx=2&sn=a60ea223de4171dd2022bc2c71e09351&chksm=e9d0cfb4dea746a2e2c470448a85df0c0e7dd059099ca52a2fec0311d12b7279b3d6f1d137be&mpshare=1&scene=24&srcid=1108FLwb8baf6cjj7Bdbd0GW#rd
 */
public class Density {

    private static final int DEFAULT_WIDTH_DP = 360;

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
        /**
         *   float targetDensity = displayMetrics.widthPixels / DEFAULT_WIDTH_DP;
         *   float targetScaleDensity = targetDensity + (appScaleDensity % appDensity);
         *   int targetDensityDpi = (int) (targetDensity * 160);
         *
         *  设计：Pixels=1080*1920 density=3.0 densityDpi=480
         *
         * targetDensity=1080/360=3
         * targetScaleDensity=3+(3%3)=3
         * targetDensityDpi=3*160=480
         *
         * Device 1: Pixels=720*1280 density=2.0 densityDpi=320
         * targetDensity=720/360=2
         * targetScaleDensity=2+(2%2)=2
         * targetDensityDpi=2*160=320
         *
         * Device 2: Pixels=1080*1920 density=2.625 densityDpi=420
         * targetDensityDpi=2.23125(small) -> 2.625(default) -> 3.01875(large) -> 3.4125(largest)
         *          差值：               0.39375          0.39375           0.39375
         *          倍数：   0.85`             1`                1.15`             1.30`
         *
         * targetDensity=1080/360=3
         * targetScaleDensity=3+(2.625 % 2.625)=3
         * targetScaleDensity=3+(3.01875 % 2.625)=3.15 (large)
         * targetDensityDpi=3*160=480
         */
        //获取目标density
        float targetDensity = displayMetrics.widthPixels / DEFAULT_WIDTH_DP;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);
        //设置Activity的density
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

}
