package com.farmer.open9527.recycleview.scroll

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class TopSmoothScroller : LinearSmoothScroller {

    constructor(context: Context?) : super(context)

    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }
}