package com.jay.androidscreencompatibility.ui.others;

import com.jay.androidscreencompatibility.R;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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

    @BindView(R.id.constraintlayout_set)
    ConstraintLayout constraintLayout;

    private ConstraintSet applyConstraintSet = new ConstraintSet();

    private ConstraintSet resetConstraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        ButterKnife.bind(this);
        initConstraintSet();
    }

    @OnClick({R.id.btn_group_visiable, R.id.btn_margin_a_gone, R.id.btnSetPlaceHolder, R.id
        .btnApply, R.id.btnReset})
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
            case R.id.btnApply:
                // 设置过渡动画
                TransitionManager.beginDelayedTransition(constraintLayout);
                applyConstraintSet6();
                break;
            case R.id.btnReset:
                resetConstraintSet();
                break;
        }
    }

    private void initConstraintSet() {
        // 可以理解为把 constraintLayout 约束克隆一份到 ConstraintSet
        applyConstraintSet.clone(constraintLayout);
        resetConstraintSet.clone(constraintLayout);
    }

    private void resetConstraintSet() {
        TransitionManager.beginDelayedTransition(constraintLayout);
        resetConstraintSet.applyTo(constraintLayout);
    }

    private void applyConstraintSet1() {
        // 情景1：当用户点击的时候，让 btn1 距离开始 8px
        applyConstraintSet.setMargin(R.id.btn1, ConstraintSet.START, 80);
    }

    private void applyConstraintSet2() {
//         优化：由于我们给每个 button 设置了边距，所以需要先移除设置的边距
        applyConstraintSet.setMargin(R.id.btn1, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn1, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn2, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn2, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn3, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn3, ConstraintSet.END, 0);

//         情景2：当用户点击的时候，让 btn1、btn2、btn3 水平居中在父布局
        applyConstraintSet.centerHorizontally(R.id.btn1, R.id.main);
        applyConstraintSet.centerHorizontally(R.id.btn2, R.id.main);
        applyConstraintSet.centerHorizontally(R.id.btn3, R.id.main);
    }

    private void applyConstraintSet3() {
        // 情景3：让 btn3 动起来，然后移动到正中心
        // 第一步：先设置四个边的边距为 0
        applyConstraintSet.setMargin(R.id.btn3, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn3, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn3, ConstraintSet.TOP, 0);
        applyConstraintSet.setMargin(R.id.btn3, ConstraintSet.BOTTOM, 0);
        // 第二步：设置水平和垂直居中
        applyConstraintSet.centerHorizontally(R.id.btn3, R.id.main);
        applyConstraintSet.centerVertically(R.id.btn3, R.id.main);
    }

    private void applyConstraintSet4() {
        // 情景4：让所有按钮的宽度都变成 600px
        applyConstraintSet.constrainWidth(R.id.btn1, 600);
        applyConstraintSet.constrainWidth(R.id.btn2, 600);
        applyConstraintSet.constrainWidth(R.id.btn3, 600);
    }

    private void applyConstraintSet5() {
//         情景5：让按钮1的宽度和高度充满整个屏幕并且让其他的视图隐藏
        applyConstraintSet.setVisibility(R.id.btn2, ConstraintSet.GONE);
        applyConstraintSet.setVisibility(R.id.btn3, ConstraintSet.GONE);
//         把 view 上的所有 constraint 都清除掉
        applyConstraintSet.clear(R.id.btn1);
//         分别设置 btn1 上下左右的约束及外边距为 0
        applyConstraintSet.connect(R.id.btn1, ConstraintSet.LEFT, R.id.main, ConstraintSet.LEFT, 0);
        applyConstraintSet
            .connect(R.id.btn1, ConstraintSet.RIGHT, R.id.main, ConstraintSet.RIGHT, 0);
        applyConstraintSet.connect(R.id.btn1, ConstraintSet.TOP, R.id.main, ConstraintSet.TOP, 0);
        applyConstraintSet.connect(R.id.btn1, ConstraintSet.BOTTOM, R.id.main, ConstraintSet
            .BOTTOM, 0);
    }

    private void applyConstraintSet6() {
        // 情景6：所有的按钮都与屏幕的顶端对齐并且水平居中
        // 第一步，先把 view 上的所有 constraint 都清除掉
        applyConstraintSet.clear(R.id.btn1);
        applyConstraintSet.clear(R.id.btn2);
        applyConstraintSet.clear(R.id.btn3);

        // 设置 btn1 左边的约束与父布局对齐
        applyConstraintSet.connect(R.id.btn1, ConstraintSet.LEFT, R.id.main, ConstraintSet.LEFT, 0);

        // 设置 btn3 右边的约束与父布局对齐
        applyConstraintSet
            .connect(R.id.btn3, ConstraintSet.RIGHT, R.id.main, ConstraintSet.RIGHT, 0);

        // 设置 btn1 & btn2 的相互约束的链关系
        applyConstraintSet
            .connect(R.id.btn2, ConstraintSet.LEFT, R.id.btn1, ConstraintSet.RIGHT, 0);
        applyConstraintSet
            .connect(R.id.btn1, ConstraintSet.RIGHT, R.id.btn2, ConstraintSet.LEFT, 0);

        // 设置 btn2 & btn3 的相互约束的链关系
        applyConstraintSet
            .connect(R.id.btn2, ConstraintSet.RIGHT, R.id.btn3, ConstraintSet.LEFT, 0);
        applyConstraintSet
            .connect(R.id.btn3, ConstraintSet.LEFT, R.id.btn2, ConstraintSet.RIGHT, 0);

        // 由于我们最开始清除了 3 个按钮的所有属性，所以我们在这里要重新设置控件的宽高
        applyConstraintSet.constrainWidth(R.id.btn1, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.constrainWidth(R.id.btn2, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.constrainWidth(R.id.btn3, ConstraintSet.WRAP_CONTENT);

        applyConstraintSet.constrainHeight(R.id.btn1, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.constrainHeight(R.id.btn2, ConstraintSet.WRAP_CONTENT);
        applyConstraintSet.constrainHeight(R.id.btn3, ConstraintSet.WRAP_CONTENT);

        // 参数分别为：设置链头和链尾的锚点的 id 和位置，设置所有链中元素的 id 到数组中，设置权重（如果有），设置链的样式
        // 注意：leftSide & rightSide 必须为 ConstraintSet.LEFT 或者 ConstraintSet.RIGHT（水平链）
        // ConstraintSet.TOP 或者 ConstraintSet.BOTTOM（竖直链）
        applyConstraintSet
            .createHorizontalChain(R.id.main, ConstraintSet.LEFT, R.id.main, ConstraintSet.RIGHT,
                new int[]{R.id.btn1, R.id.btn2, R.id.btn3}, null, ConstraintWidget.CHAIN_PACKED);

        // 设置带偏移量的 CHAIN_PACKED
        applyConstraintSet.setHorizontalBias(R.id.btn1, 0.1f);

        // 将设置完的约束，再应用到 constraintLayout 上
        applyConstraintSet.applyTo(constraintLayout);
    }

    private void applyConstraintSet7() {


        //-------------------------------------------------------------------------


        //-------------------------------------------------------------------------


        //-------------------------------------------------------------------------


        //-------------------------------------------------------------------------


        //-------------------------------------------------------------------------


    }
}
