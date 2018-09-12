package com.jay.androidscreencompatibility.utils;

import android.os.Build;

import java.io.File;

public final class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return whether device is rooted.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
            "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the version name of device's system.
     *
     * @return the version name of device's system
     */
    public static String getDeviceVersionName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Return version code of device's system.
     *
     * @return version code of device's system
     */
    public static int getDeviceVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * Return name of device's system.
     *
     * @return name of device's system
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * Return model of device.
     *
     * @return model of device
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }
}
