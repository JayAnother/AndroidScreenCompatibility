package com.jay.androidscreencompatibility.utils;

import com.jay.androidscreencompatibility.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.core.content.ContextCompat;

public class ScreenBox extends View {

    static final float CM_PER_INCH = 2.54f;

    private static final String AXIS_TEXT_FORMAT = "%dpx (%ddp)  ~ %.1f\" (%.1fcm)";

    private static final int DIMEN_LINE_MARGIN = 25;

    private static final int DIMEN_LINE_DESC_MARGIN = DIMEN_LINE_MARGIN + 5;

    private int mWidth;

    private int mWidthDp;

    private int mHeight;

    private int mHeightDp;

    private float mWidthInches;

    private float mHeightInches;

    private double mDiagonalInches;

    private Paint mPaint;

    private float mDensity;

    public ScreenBox(Context context) {
        super(context);
        init();
    }

    public ScreenBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScreenBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDensity = getContext().getResources().getDisplayMetrics().density;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        mPaint.setStrokeWidth(1 * mDensity);

        mPaint.setTextSize(16 * mDensity);

        canvas.drawLine(
            mWidth - (DIMEN_LINE_MARGIN * mDensity),
            0,
            mWidth - (DIMEN_LINE_MARGIN * mDensity),
            mHeight,
            mPaint);
        canvas.drawLine(
            0,
            mHeight - (DIMEN_LINE_MARGIN * mDensity),
            mWidth,
            mHeight - (DIMEN_LINE_MARGIN * mDensity), mPaint);

        canvas.save();
        canvas.rotate(270, mWidth - (DIMEN_LINE_DESC_MARGIN * mDensity), mHeight / 2);
        canvas.drawText(
            String.format(AXIS_TEXT_FORMAT, mHeight, mHeightDp, mHeightInches,
                mHeightInches * CM_PER_INCH),
            mWidth - (DIMEN_LINE_DESC_MARGIN * mDensity),
            mHeight / 2,
            mPaint);
        canvas.restore();

        canvas.drawText(
            String.format(AXIS_TEXT_FORMAT, mWidth, mWidthDp, mWidthInches,
                mWidthInches * CM_PER_INCH),
            mWidth / 2,
            mHeight - (DIMEN_LINE_DESC_MARGIN * mDensity),
            mPaint);

        //Diagonal
        canvas.drawLine(0, mHeight, mWidth, 0, mPaint);
        canvas.save();
        float angle = (float) Math.toDegrees(Math.atan2(-mHeight, mWidth));
        canvas.rotate(angle, mWidth / 2, mHeight / 2);
        canvas.drawText(
            String.format("%.1fin (%.1fcm)", mDiagonalInches,
                mDiagonalInches * CM_PER_INCH),
            mWidth / 2,
            mHeight / 2 + mPaint.getTextSize(),
            mPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        mWidthDp = (int) (w / mDensity);
        mHeightDp = (int) (h / mDensity);

        if (!isInEditMode()) {
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
            mWidthInches = mWidth / dm.xdpi;
            mHeightInches = mHeight / dm.ydpi;
            mDiagonalInches = Math.sqrt(Math.pow(mWidthInches, 2) + Math.pow(mHeightInches, 2));
        }
    }
}
