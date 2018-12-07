package com.jay.androidscreencompatibility.ui.others;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.main.BaseActivity;
import com.jay.androidscreencompatibility.utils.ScreenBox;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

public class ScreenInfoActivityPlus extends BaseActivity {

    @BindView(R.id.screen_box)
    ScreenBox screenBox;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvInfo.setText(ScreenUtils.getScreenInfo3(this));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_screen_info_plus;
    }
}
