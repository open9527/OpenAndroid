package com.farmer.open9527.recycleview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class CustomItemDecoration :ItemDecoration {
    constructor() : super()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
//        val lastPosition = state.itemCount - 1
//        val childLayoutPosition = parent.getChildLayoutPosition(view)
//        if (childLayoutPosition % 2 == 0) {
//            outRect.left = 15
//            outRect.right = 4
//        } else {
//            outRect.left = 4
//            outRect.right = 15
//        }
//        outRect.top = 10
    }
}