package com.vatsal.imagezoomer;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by - Vatsal
 * on 02/06/16 at 3:04 PM
 */

public class ImageZoomButton extends ImageButton {
    private Drawable mForegroundDrawable;
    private Context context;
    private boolean mModeReverse;
    private int mDuration;
    private Rect mCachedBounds = new Rect();

    public ImageZoomButton(Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public ImageZoomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImageZoomButton, 0, 0);

        try {
            mModeReverse = a.getBoolean(R.styleable.ImageZoomButton_modeReverse, false);
            mDuration = a.getInt(R.styleable.ImageZoomButton_duration, 350);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }
        this.init();
    }

    public ImageZoomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.init();
    }

    private void init() {
        this.setBackgroundColor(0);
        this.setPadding(0, 0, 0, 0);
        TypedArray a = this.getContext().obtainStyledAttributes(new int[]{16843534});
        this.mForegroundDrawable = a.getDrawable(0);
        this.mForegroundDrawable.setCallback(this);
        a.recycle();
    }

    private boolean getModeReverse() {
        return mModeReverse;
    }

    private long getDuration() {
        return (long) mDuration;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mForegroundDrawable.isStateful()) {
            this.mForegroundDrawable.setState(this.getDrawableState());
        }

        this.invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mForegroundDrawable.setBounds(this.mCachedBounds);
        this.mForegroundDrawable.draw(canvas);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mCachedBounds.set(0, 0, w, h);
    }

    public boolean performClick() {
        Activity activity = (Activity) this.context;
        if (getModeReverse())
            ZoomAnimation.zoomReverse(this, activity, getDuration());
        else
            ZoomAnimation.zoom(this, activity, getDuration());
        return super.performClick();
    }
}

