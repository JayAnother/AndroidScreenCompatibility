package com.jay.androidscreencompatibility.ui.main;

import com.jay.androidscreencompatibility.ui.adaptation_density.DensityUtils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityUtils.setDefault(this);
        setContentView(getLayout());
        ButterKnife.bind(this);
    }

    protected abstract int getLayout();
}
