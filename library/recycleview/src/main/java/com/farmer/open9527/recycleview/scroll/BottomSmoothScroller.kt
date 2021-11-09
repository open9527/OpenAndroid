package com.farmer.open9527.recycleview.scroll

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller


/**
 *@author   open_9527
 *Create at 2021/11/4
 *
 *
 *
 *
 **/
class BottomSmoothScroller : LinearSmoothScroller {

    constructor(context: Context?) : super(context)

    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_END
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_END
    }
    //TODO eg:
    /*  private fun onScroller(
          linearLayoutManager: LinearLayoutManager,
          context: Context,
          position: Int
      ) {
          val bottomSmoothScroller = BottomSmoothScroller(context)
          bottomSmoothScroller.targetPosition = position
          linearLayoutManager.startSmoothScroll(bottomSmoothScroller)
      }*/
}