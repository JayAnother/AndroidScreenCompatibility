package com.jay.androidscreencompatibility.ui.adaptation_density;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.utils.ScreenUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class DensityAdaptActivity extends AppCompatActivity {

    @BindView(R.id.tv_data_info)
    TextView tvDataInfo;

    @BindView(R.id.tv_screen_info)
    TextView tvScreenInfo;

    @BindView(R.id.tv_shape)
    TextView tvShape;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Density.setDensity(getApplication(), this);
        setContentView(R.layout.activity_tt_adapt_test);
        ButterKnife.bind(this);
        tvScreenInfo.setText("Screen Info: \n\n" + ScreenUtils.getScreenInfo2());

        ViewTreeObserver vto = tvShape.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                int height = tvShape.getMeasuredHeight();
                int width = tvShape.getMeasuredWidth();
                tvDataInfo
                    .setText("Widget Info: \n\n" + ScreenUtils.getScreenWidgetInfo(height, width));
                return true;
            }
        });
    }
}
