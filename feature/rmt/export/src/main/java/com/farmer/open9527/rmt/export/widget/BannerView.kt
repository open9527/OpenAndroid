package com.farmer.open9527.rmt.export.widget

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager2.widget.ViewPager2
import com.farmer.open9527.rmt.export.R
import com.zhpan.bannerview.BannerViewPager

class BannerView : BannerViewPager<Nothing> {

    constructor(context: Context) : this(context, null, -1)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val mViewPager: ViewPager2 = findViewById(R.id.vp_main)
        if (mViewPager != null) {
            mViewPager.importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_NO
        }
    }
}