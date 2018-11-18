package com.jay.androidscreencompatibility.ui.adaptation_view.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/11/12 0012.
 */

public class ScalableRelativeLayout extends RelativeLayout {

    private boolean flag = true;

    public ScalableRelativeLayout(Context context) {
        super(context);
    }

    public ScalableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (flag) {
            int childCount = this.getChildCount();
            float scaleX = UIUtils.getInstance(getContext()).getHorizontalScalValue();
            float scaleY = UIUtils.getInstance(getContext()).getVerticalScaleValue();
            for (int i = 0; i < childCount; i++) {
                View child = this.getChildAt(i);//每个子控件
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);

                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);

                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }
            flag = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
