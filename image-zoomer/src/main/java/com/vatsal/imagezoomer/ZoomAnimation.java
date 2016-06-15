package com.vatsal.imagezoomer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by - Vatsal
 * on 02/06/16 at 3:05 PM
 */

public final class ZoomAnimation {
    private Animator mCurrentAnimator;
    private ImageView mImageZoom;
    private Activity activity;

    public ZoomAnimation(Activity activity) {
        this.activity = activity;
    }

    public void zoomReverse(View view, long duration) {
        zoomImageFromThumbReverse(view, activity, duration);
    }

    public void zoom(View view, long duration) {
        zoomImageFromThumb(view, activity, duration);
    }

    public AnimatorSet set1;
    public AnimatorSet set2;

    private void zoomImageFromThumbReverse(final View thumbView, Activity activity, long duration) {

        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        ContentFrameLayout container = (ContentFrameLayout) activity.findViewById(android.R.id.content);
        mImageZoom = new ImageView(thumbView.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        mImageZoom.setLayoutParams(params);
        mImageZoom.setImageDrawable(((ImageButton) thumbView).getDrawable());
        mImageZoom.setVisibility(View.GONE);
        container.addView(mImageZoom);
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        Point globalOffset = new Point();
        thumbView.getGlobalVisibleRect(startBounds);
        container.getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        final float startScale;
        float set;
        float startScaleFinal;
        if ((float) finalBounds.width() / (float) finalBounds.height() > (float) startBounds.width() / (float) startBounds.height()) {
            startScale = (float) startBounds.height() / (float) finalBounds.height();
            set = startScale * (float) finalBounds.width();
            startScaleFinal = (set - (float) startBounds.width()) / 2.0F;
            startBounds.left = (int) ((float) startBounds.left - startScaleFinal);
            startBounds.right = (int) ((float) startBounds.right + startScaleFinal);
        } else {
            startScale = (float) startBounds.width() / (float) finalBounds.width();
            set = startScale * (float) finalBounds.height();
            startScaleFinal = (set - (float) startBounds.height()) / 2.0F;
            startBounds.top = (int) ((float) startBounds.top - startScaleFinal);
            startBounds.bottom = (int) ((float) startBounds.bottom + startScaleFinal);
        }

        // thumbView.setAlpha(0.0F);
        // mImageZoom.setVisibility(View.VISIBLE);
        mImageZoom.setPivotX(0.0F);
        mImageZoom.setPivotY(0.0F);
        set2 = new AnimatorSet();
        set2.setDuration(duration);
        set2.play(ObjectAnimator.ofFloat(mImageZoom, View.X, new float[]{(float) startBounds.left, (float) finalBounds.left})).with(ObjectAnimator.ofFloat(mImageZoom, View.Y, new float[]{(float) startBounds.top, (float) finalBounds.top})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_X, new float[]{startScale, 1.0F})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_Y, new float[]{startScale, 1.0F}));
        set2.setInterpolator(new ReverseInterpolator());
        set2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mImageZoom.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mImageZoom.setVisibility(View.GONE);
            }
        });

        set1 = new AnimatorSet();
        set1.setDuration(duration);
        set1.play(ObjectAnimator.ofFloat(mImageZoom, View.X, new float[]{(float) startBounds.left, (float) finalBounds.left})).with(ObjectAnimator.ofFloat(mImageZoom, View.Y, new float[]{(float) startBounds.top, (float) finalBounds.top})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_X, new float[]{startScale, 1.0F})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_Y, new float[]{startScale, 1.0F}));
        set1.setInterpolator(new DecelerateInterpolator());
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImageZoom.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animator animation) {
                //   thumbView.setAlpha(1.0F);
                //    mImageZoom.setVisibility(8);

                set2.start();
                mCurrentAnimator = null;
            }

            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
                mImageZoom.setVisibility(View.GONE);
            }
        });
        set1.start();

        mCurrentAnimator = set1;
        mImageZoom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                mImageZoom.setBackgroundColor(0);
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(mImageZoom, View.X, new float[]{(float) startBounds.left})).with(ObjectAnimator.ofFloat(mImageZoom, View.Y, new float[]{(float) startBounds.top})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_X, new float[]{startScale})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_Y, new float[]{startScale}));
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1.0F);
                        mImageZoom.setVisibility(View.GONE);
                        if (mCurrentAnimator != null) {
                            mCurrentAnimator.cancel();
                        }
                        mCurrentAnimator = null;
                    }

                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1.0F);
                        mImageZoom.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    private void zoomImageFromThumb(final View thumbView, Activity activity, long duration) {

        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        ContentFrameLayout container = (ContentFrameLayout) activity.findViewById(android.R.id.content);
        mImageZoom = new ImageView(thumbView.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        mImageZoom.setLayoutParams(params);
        mImageZoom.setImageDrawable(((ImageButton) thumbView).getDrawable());
        mImageZoom.setVisibility(View.GONE);
        container.addView(mImageZoom);
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        Point globalOffset = new Point();
        thumbView.getGlobalVisibleRect(startBounds);
        container.getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        final float startScale;
        float set;
        float startScaleFinal;
        if ((float) finalBounds.width() / (float) finalBounds.height() > (float) startBounds.width() / (float) startBounds.height()) {
            startScale = (float) startBounds.height() / (float) finalBounds.height();
            set = startScale * (float) finalBounds.width();
            startScaleFinal = (set - (float) startBounds.width()) / 2.0F;
            startBounds.left = (int) ((float) startBounds.left - startScaleFinal);
            startBounds.right = (int) ((float) startBounds.right + startScaleFinal);
        } else {
            startScale = (float) startBounds.width() / (float) finalBounds.width();
            set = startScale * (float) finalBounds.height();
            startScaleFinal = (set - (float) startBounds.height()) / 2.0F;
            startBounds.top = (int) ((float) startBounds.top - startScaleFinal);
            startBounds.bottom = (int) ((float) startBounds.bottom + startScaleFinal);
        }

        // thumbView.setAlpha(0.0F);
        // mImageZoom.setVisibility(View.VISIBLE);
        mImageZoom.setPivotX(0.0F);
        mImageZoom.setPivotY(0.0F);

        set1 = new AnimatorSet();
        set1.setDuration(duration);
        set1.play(ObjectAnimator.ofFloat(mImageZoom, View.X, new float[]{(float) startBounds.left, (float) finalBounds.left})).with(ObjectAnimator.ofFloat(mImageZoom, View.Y, new float[]{(float) startBounds.top, (float) finalBounds.top})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_X, new float[]{startScale, 1.0F})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_Y, new float[]{startScale, 1.0F}));
        set1.setInterpolator(new DecelerateInterpolator());
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mImageZoom.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
                mImageZoom.setVisibility(View.GONE);
            }
        });
        set1.start();

        mCurrentAnimator = set1;
        mImageZoom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                mImageZoom.setBackgroundColor(0);
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator.ofFloat(mImageZoom, View.X, new float[]{(float) startBounds.left})).with(ObjectAnimator.ofFloat(mImageZoom, View.Y, new float[]{(float) startBounds.top})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_X, new float[]{startScale})).with(ObjectAnimator.ofFloat(mImageZoom, View.SCALE_Y, new float[]{startScale}));
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1.0F);
                        mImageZoom.setVisibility(View.GONE);
                        if (mCurrentAnimator != null) {
                            mCurrentAnimator.cancel();
                        }
                        mCurrentAnimator = null;
                    }

                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1.0F);
                        mImageZoom.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

}
