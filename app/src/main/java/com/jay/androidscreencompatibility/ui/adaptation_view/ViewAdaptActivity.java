package com.jay.androidscreencompatibility.ui.adaptation_view;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class ViewAdaptActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scalable_view);
        ButterKnife.bind(this);
    }
}
