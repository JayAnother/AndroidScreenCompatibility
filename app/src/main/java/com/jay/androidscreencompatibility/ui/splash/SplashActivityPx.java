package com.jay.androidscreencompatibility.ui.splash;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.MainActivity;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SplashActivityPx extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_px);

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText(ScreenUtils.getScreenInfo());
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivityPx.this, SplashActivity.class));
            }
        });
        TextView tvPxOrDp = findViewById(R.id.tv_dp);
        tvPxOrDp.setText(ScreenUtils.px2dp(100)+" dp");
        tvPxOrDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivityPx.this, MainActivity.class));
            }
        });
    }
}
