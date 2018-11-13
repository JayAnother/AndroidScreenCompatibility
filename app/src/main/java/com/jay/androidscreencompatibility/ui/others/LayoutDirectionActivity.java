package com.jay.androidscreencompatibility.ui.others;

import com.jay.androidscreencompatibility.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayoutDirectionActivity extends AppCompatActivity {

    @BindView(R.id.tv_direction)
    TextView tvDirection;

    @BindView(R.id.rl_direction_left_right)
    RelativeLayout rlDirectionLeftRight;

    @BindView(R.id.rl_direction_start_end)
    RelativeLayout rlDirectionStartEnd;

    boolean isLeftRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_direction);
        ButterKnife.bind(this);
    }

    @SuppressLint("SetTextI18n")
    @OnClick(R.id.btn_exchange)
    public void onViewClicked() {
        if (isLeftRight) {
            tvDirection.setText("Current Direction is Left-To-Right");
            tvDirection.setTextDirection(View.TEXT_DIRECTION_LTR);
            rlDirectionLeftRight.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            rlDirectionStartEnd.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }else{
            tvDirection.setText("Current Direction is Right-To-Left");
            tvDirection.setTextDirection(View.TEXT_DIRECTION_RTL);
            rlDirectionLeftRight.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            rlDirectionStartEnd.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        isLeftRight = !isLeftRight;
    }
}
