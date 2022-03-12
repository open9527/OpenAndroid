package com.farmer.open9527.common.widget.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class CommonViewPager : ViewPager {
    private var mHasScroll: Boolean = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return mHasScroll && super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return mHasScroll && super.onTouchEvent(ev)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, mHasScroll)
    }

    fun setScroll(hasScroll: Boolean) {
        mHasScroll = hasScroll;
    }
}