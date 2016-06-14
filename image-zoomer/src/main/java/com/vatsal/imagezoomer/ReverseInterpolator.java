package com.vatsal.imagezoomer;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by - Vatsal
 * on 02/06/16 at 9:27 PM
 * in voidz
 */

class ReverseInterpolator implements Interpolator {

    private Interpolator interpolator;

    public ReverseInterpolator(Interpolator interpolator) {

        this.interpolator = interpolator;
    }

    public ReverseInterpolator() {
        this(new DecelerateInterpolator());
    }

    @Override
    public float getInterpolation(float input) {
        return 1 - interpolator.getInterpolation(input);
    }
}
