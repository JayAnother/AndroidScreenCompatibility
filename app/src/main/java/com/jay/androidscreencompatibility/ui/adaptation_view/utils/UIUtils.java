package com.jay.androidscreencompatibility.ui.adaptation_view.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/11/12 0012.
 */

public class UIUtils {

    //参考设备分辨率
    private static final float STANDARD_WIDTH = 1080F;

    private static final float STANDARD_HEIGHT = 1920F;

    private static final String MIDEN_CLASS = "com.android.internal.R&dimen";

    //实际设备的分辨率
    private static float displayMetricsWidth;

    private static float displayMetricsHeight;

    //单例
    private static UIUtils instance;

    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            //避免内存泄漏
            instance = new UIUtils(context.getApplicationContext());
        }
        return instance;
    }

    private UIUtils(Context context) {
        WindowManager windowManager = (WindowManager) context
            .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
            //获取状态框的信息
            int systemBarHight = getSystemBarHeight(context);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                //平板或横屏
                displayMetricsWidth = (float) displayMetrics.heightPixels;
                displayMetricsHeight = (float) displayMetrics.heightPixels - systemBarHight;
            } else {
                displayMetricsWidth = (float) displayMetrics.widthPixels;
                displayMetricsHeight = (float) displayMetrics.heightPixels - systemBarHight;
            }
        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context, MIDEN_CLASS, "system_bar_height", 48);
    }

    private int getValue(Context context, String attrGroupClass, String system_bar_height,
        int defValue) {
        try {
            Class e = Class.forName(attrGroupClass);
            Object obj = e.newInstance();
            Field field = e.getField(system_bar_height);
            int x = Integer.parseInt(field.get(obj).toString());
            //x获取的ID转为对应的宽高值
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    //水平缩放比
    public float getHorizontalScalValue() {
        return (displayMetricsWidth) / STANDARD_WIDTH;
    }

    //垂直缩放比
    public float getVerticalScaleValue() {
        return (displayMetricsHeight) / STANDARD_HEIGHT;
    }
}
