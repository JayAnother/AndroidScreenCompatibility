package com.jay.androidscreencompatibility.ui.adaptation_view;

import com.jay.androidscreencompatibility.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewAdaptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scalable_view);
    }
}
