package com.jay.androidscreencompatibility.ui.adaptation_density;

import com.jay.androidscreencompatibility.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DensityAdaptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(),this);
        setContentView(R.layout.activity_tt_adapt);
    }
}
