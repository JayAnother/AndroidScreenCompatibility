package com.jay.androidscreencompatibility.ui.adaptation_bar;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.BaseActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 适配android 9.0凹口屏幕
 */
public class DisplayCutoutActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_display_cutout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1,设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //2，让内容区填充到刘海区
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams attribute = getWindow().getAttributes();
            /**
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 让内容区填充到刘海区
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容区填充到刘海区
             */
            attribute.layoutInDisplayCutoutMode = WindowManager.LayoutParams
                .LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(attribute);
        }
        //3,将页面设置为沉浸式
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        int visibility = getWindow().getDecorView().getSystemUiVisibility();
        visibility |= flags;
        getWindow().getDecorView().setSystemUiVisibility(visibility);
        super.onCreate(savedInstanceState);

    }
}
