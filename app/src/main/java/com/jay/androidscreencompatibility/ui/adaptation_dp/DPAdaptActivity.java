package com.jay.androidscreencompatibility.ui.adaptation_dp;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.BaseActivity;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DPAdaptActivity extends BaseActivity {

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvInfo.setText("DP: \n" + ScreenUtils.getScreenInfo2());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dp_adapt;
    }
}
