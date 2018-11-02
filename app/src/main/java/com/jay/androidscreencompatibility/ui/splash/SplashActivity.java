package com.jay.androidscreencompatibility.ui.splash;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.DemoActivity;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText(ScreenUtils.getScreenInfo());
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, SplashActivityPx.class));
            }
        });
        TextView tvPxOrDp = findViewById(R.id.tv_px);
        tvPxOrDp.setText("H: " + ScreenUtils.dp2px(100)+" px");
        tvPxOrDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, DemoActivity.class));
            }
        });
    }
}
