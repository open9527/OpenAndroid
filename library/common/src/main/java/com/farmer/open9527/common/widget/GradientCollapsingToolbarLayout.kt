package com.farmer.open9527.common.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.CollapsingToolbarLayout


/**
 *@author   open_9527
 *Create at 2021/11/8
 *
 **/
class GradientCollapsingToolbarLayout : CollapsingToolbarLayout {

    private var mListener: OnScrimsListener? = null

    private var mScrimsShown = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setScrimsShown(shown: Boolean) {
        super.setScrimsShown(shown)
        // 判断渐变状态是否改变了
        if (mScrimsShown != shown) {
            mScrimsShown = shown
            mListener?.onScrimsStateChange(this, mScrimsShown)
        }
    }


    fun setOnScrimsListener(listener: OnScrimsListener) {
        mListener = listener
    }

    interface OnScrimsListener {
        fun onScrimsStateChange(
            layout: GradientCollapsingToolbarLayout?,
            shown: Boolean
        )
    }
}