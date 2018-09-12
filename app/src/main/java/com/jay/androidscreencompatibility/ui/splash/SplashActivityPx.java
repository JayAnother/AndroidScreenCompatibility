package com.jay.androidscreencompatibility.ui.splash;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplashActivityPx extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_px);

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText(ScreenUtils.getScreenInfo());

        TextView tvPxOrDp = findViewById(R.id.tv_dp);
        tvPxOrDp.setText(ScreenUtils.px2dp(100)+" dp");
    }
}
