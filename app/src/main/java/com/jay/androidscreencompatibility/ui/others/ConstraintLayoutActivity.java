package com.jay.androidscreencompatibility.ui.others;

import com.jay.androidscreencompatibility.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Placeholder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConstraintLayoutActivity extends AppCompatActivity {

    @BindView(R.id.btn_margin_a)
    Button btnMarginA;

    @BindView(R.id.group)
    Group group;

    @BindView(R.id.placeHolder)
    Placeholder placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_group_visiable, R.id.btn_margin_a_gone, R.id.btnSetPlaceHolder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_margin_a_gone:
                btnMarginA
                    .setVisibility(
                        btnMarginA.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
            case R.id.btn_group_visiable:
                group
                    .setVisibility(
                        group.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
            case R.id.btnSetPlaceHolder:
                View content = placeHolder.getContent();
                if (content == null) {
                    placeHolder.setContentId(R.id.tvOrigin);
                }
                break;
        }
    }

}
