package com.jay.androidscreencompatibility.utils;

import com.jay.androidscreencompatibility.MyApp;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;

import androidx.annotation.NonNull;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class ScreenUtils {

    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getScreenInfo() {
        String str = "";
        double screenInch = getScreenInch();
        int screenWidthPx = getScreenWidthInPixel();
        int screenHeightPx = getScreenHeightInPixel();
        float screenDensity = getScreenDensity();
        float screenScaledDensity = getScreenScaledDensity();
        float screenDensityDpi = getScreenDensityDpi();
        float screenWidthDp = getScreenWidthInDp();
        float screenHeightDp = getScreenHeightInDp();
        str += "设备名称：" + DeviceUtils.getDeviceModel() + " (" + DeviceUtils.getDeviceBrand() + ")\n";
        str += "系统版本：" + DeviceUtils.getDeviceVersionName() + " (" + DeviceUtils
            .getDeviceVersionCode() + ")\n";
        str += "屏幕尺寸（inch）：" + String.valueOf(screenInch) + "\n";
        str += "屏幕大小（px）：" + screenHeightPx + " * " + screenWidthPx + "\n";
        str += "屏幕大小（dp）：" + screenHeightDp + " * " + screenWidthDp + "\n";
        str += "屏幕密度 (density)：" + String.valueOf(screenDensity) + "\n";
        str += "屏幕密度 (scaledDensity)：" + String.valueOf(screenScaledDensity) + "\n";
        str += "像素密度 (dpi) ：" + String.valueOf(screenDensityDpi);
        return str;
    }

    public static String getScreenInfo2() {
        String str = "";
        double screenInch = getScreenInch();
        int screenWidthPx = getScreenWidthInPixel();
        int screenHeightPx = getScreenHeightInPixel();
        float screenDensity = getScreenDensity();
        float screenDensityDpi = getScreenDensityDpi();
        float screenWidthDp = getScreenWidthInDp();
        float screenHeightDp = getScreenHeightInDp();
        str += "(inch)：" + String.valueOf(screenInch) + "\n";
        str += "(px)：" + screenHeightPx + " * " + screenWidthPx + "\n";
        str += "(dp)：" + screenHeightDp + " * " + screenWidthDp + "\n";
        str += "(density)：" + String.valueOf(screenDensity) + "\n";
        str += "(dpi)：" + String.valueOf(screenDensityDpi);
        return str;
    }

    public static String getScreenWidgetInfo(int height, int width) {
        String str = "";
        double widthRatio = (double) width / (double) getScreenWidthInPixel();
        double heightRatio = (double) height / (double) getScreenHeightInPixel();
        str += "W：" + width + "\n";
        str += "H：" + height + "\n";
        str += "W/S：" + getScaledDouble(widthRatio, 4, 4) + "\n";
        str += "H/S：" + getScaledDouble(heightRatio, 4, 4);
        return str;
    }

    private static int[] getMeasuredViewSize(View view) {
        int size[] = new int[2];
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        size[0] = view.getMeasuredWidth();
        size[1] = view.getMeasuredHeight();
        return size;
    }

    private static double getScaledDouble(double value, int scale, int mode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, mode);
        return bd.doubleValue();
    }

    /**
     * Return the screen size in inch
     *
     * @return the size of screen, in inch
     */
    public static double getScreenInch() {
        double mInch = 0;
        int realWidth = 0, realHeight = 0;
        DisplayMetrics metrics = getDisplayMetrics();
        realHeight = getScreenHeightInPixel();
        realWidth = getScreenWidthInPixel();
        mInch = formatDouble(Math.sqrt((realWidth / metrics.xdpi) * (realWidth / metrics.xdpi) +
            (realHeight / metrics.ydpi) * (realHeight / metrics.ydpi)), 1);
        return mInch;
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidthInPixel() {
        WindowManager wm = getWindowManager();
        if (wm == null) {
            return getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidthInDp() {
        return (int) (getScreenWidthInPixel() / getScreenDensity());
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    public static int getScreenHeightInPixel() {
        WindowManager wm = getWindowManager();
        if (wm == null) {
            return getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /**
     * Return the height of screen, in dp.
     *
     * @return the height of screen, in dp
     */
    public static int getScreenHeightInDp() {
        return (int) (getScreenHeightInPixel() / getScreenDensity());
    }

    /**
     * get DisplayMetrics
     *
     * @return DisplayMetrics
     */
    private static DisplayMetrics getDisplayMetrics() {
        return MyApp.getContect().getResources().getDisplayMetrics();
    }

    /**
     * get WindowManager
     *
     * @return WindowManager
     */
    private static WindowManager getWindowManager() {
        return (WindowManager) MyApp.getContect()
            .getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * Return the density of screen.
     *
     * @return the density of screen
     */
    public static float getScreenDensity() {
        return getDisplayMetrics().density;
    }

    /**
     * Return the scaledDensity of screen.
     *
     * @return the scaledDensity of screen
     */
    public static float getScreenScaledDensity() {
        return getDisplayMetrics().scaledDensity;
    }

    /**
     * Return the screen density expressed as dots-per-inch.
     *
     * @return the screen density expressed as dots-per-inch
     */
    public static int getScreenDensityDpi() {
        return getDisplayMetrics().densityDpi;
    }

    /**
     * Set full screen.
     *
     * @param activity The activity.
     */
    public static void setFullScreen(@NonNull final Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * Set non full screen.
     *
     * @param activity The activity.
     */
    public static void setNonFullScreen(@NonNull final Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * Toggle full screen.
     *
     * @param activity The activity.
     */
    public static void toggleFullScreen(@NonNull final Activity activity) {
        int fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & fullScreenFlag) == fullScreenFlag) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * Return whether screen is full.
     *
     * @param activity The activity.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFullScreen(@NonNull final Activity activity) {
        int fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        return (activity.getWindow().getAttributes().flags & fullScreenFlag) == fullScreenFlag;
    }

    /**
     * Set the screen to landscape.
     *
     * @param activity The activity.
     */
    public static void setLandscape(@NonNull final Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * Set the screen to portrait.
     *
     * @param activity The activity.
     */
    public static void setPortrait(@NonNull final Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * Return whether screen is landscape.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isLandscape() {
        return MyApp.getContect().getResources().getConfiguration().orientation
            == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * Return whether screen is portrait.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPortrait() {
        return MyApp.getContect().getResources().getConfiguration().orientation
            == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * Return the rotation of screen.
     *
     * @param activity The activity.
     * @return the rotation of screen
     */
    public static int getScreenRotation(@NonNull final Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    /**
     * Return the bitmap of screen.
     *
     * @param activity The activity.
     * @return the bitmap of screen
     */
    public static Bitmap screenShot(@NonNull final Activity activity) {
        return screenShot(activity, false);
    }

    /**
     * Return the bitmap of screen.
     *
     * @param activity          The activity.
     * @param isDeleteStatusBar True to delete status bar, false otherwise.
     * @return the bitmap of screen
     */
    public static Bitmap screenShot(@NonNull final Activity activity, boolean isDeleteStatusBar) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.setWillNotCacheDrawing(false);
        Bitmap bmp = decorView.getDrawingCache();
        if (bmp == null) return null;
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap ret;
        if (isDeleteStatusBar) {
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            int statusBarHeight = resources.getDimensionPixelSize(resourceId);
            ret = Bitmap.createBitmap(
                bmp,
                0,
                statusBarHeight,
                dm.widthPixels,
                dm.heightPixels - statusBarHeight
            );
        } else {
            ret = Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels);
        }
        decorView.destroyDrawingCache();
        return ret;
    }

    /**
     * Return whether screen is locked.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isScreenLock() {
        KeyguardManager km =
            (KeyguardManager) MyApp.getContect().getSystemService(Context.KEYGUARD_SERVICE);
        return km != null && km.inKeyguardRestrictedInputMode();
    }

    /**
     * Return whether device is tablet.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isTablet() {
        return (MyApp.getContect().getResources().getConfiguration().screenLayout
            & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * The Double type retains the decimal of the specified number of digits, returning a double
     * type (rounded)
     *
     * @param doubleNum double number
     * @param newScale  newScale is the specified number of digits
     */
    private static double formatDouble(double doubleNum, int newScale) {
        BigDecimal bd = new BigDecimal(doubleNum);
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Convert dp to px
     *
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * getScreenDensity() + 0.5f);
    }

    /**
     * Convert px to dp
     *
     * @param pxValue px
     * @return dp
     */
    public static int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }

    /**
     * Convert sp to px
     *
     * @param spValue sp
     * @return px
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * getScreenScaledDensity() + 0.5f);
    }

    /**
     * Convert px to sp
     *
     * @param pxValue px
     * @return sp
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / getScreenScaledDensity() + 0.5f);
    }
}
