package com.jay.androidscreencompatibility.ui.others;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.DemoActivity;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScreenInfohActivity extends AppCompatActivity {

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.tv_px)
    TextView tvPx;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_info);
        ButterKnife.bind(this);
        tvInfo.setText(ScreenUtils.getScreenInfo());
        tvPx.setText("H: " + ScreenUtils.dp2px(100) + " px");
    }

}
