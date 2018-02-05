package com.butter.dinnerssc.ui.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by hang.zhao on 2016-12-05.
 */

public class FreeViewPager extends ViewPager {

    private boolean isScrollable = true;

    public FreeViewPager(Context context) {
        super(context);
    }

    public FreeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isScrollable)
            return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isScrollable)
            return false;
        return super.onTouchEvent(ev);
    }
}
