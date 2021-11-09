package com.farmer.open9527.common.widget.viewpager

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class CommonPagerAdapter : PagerAdapter {
    private var mCount: Int = 0
    private var mEnableDestroyItem: Boolean = false

    constructor(count: Int, enableDestroyItem: Boolean) : super() {
        mCount = count
        mEnableDestroyItem = enableDestroyItem
    }

    override fun getCount(): Int {
        return mCount
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return container.getChildAt(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (mEnableDestroyItem) {
            container.removeView(`object` as View)
        }
    }


}