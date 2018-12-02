package com.jay.androidscreencompatibility.ui.adaptation_density;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.BaseActivity;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class DensityAdaptActivity extends AppCompatActivity {

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityUtils.setOrientation(this, DensityUtils.Orientation.VERTICAL);
        setContentView(R.layout.activity_tt_adapt);
        ButterKnife.bind(this);
        tvInfo.setText("Density: \n" + ScreenUtils.getScreenInfo2());
    }

}
