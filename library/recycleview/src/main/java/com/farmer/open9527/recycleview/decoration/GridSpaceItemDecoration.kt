package com.farmer.open9527.recycleview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class GridSpaceItemDecoration : ItemDecoration {

    private var mSpanCount = 0
    private var mSpacing = 0

    private var mIncludeEdge = false

    private var mStartFromSize = 0
    private var mEndFromSize = 0

    private var fullPosition = -1


    constructor(spacing: Int) : this(spacing, true)


    constructor(spacing: Int, includeEdge: Boolean) {
        mSpacing = spacing
        mIncludeEdge = includeEdge
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val lastPosition = state.itemCount - 1
        var position = parent.getChildAdapterPosition(view)
        if (mStartFromSize <= position && position <= lastPosition - mEndFromSize) {
            var spanGroupIndex = -1
            var column = 0
            var fullSpan = false
            val layoutManager = parent.layoutManager
            if (layoutManager is GridLayoutManager) {
                val spanSizeLookup = layoutManager.spanSizeLookup
                val spanCount = layoutManager.spanCount
                val spanSize = spanSizeLookup.getSpanSize(position)
                mSpanCount = spanCount / spanSize
                val spanIndex = spanSizeLookup.getSpanIndex(position, spanCount)
                column = spanIndex / spanSize
                spanGroupIndex =
                    spanSizeLookup.getSpanGroupIndex(position, spanCount) - mStartFromSize
            } else if (layoutManager is StaggeredGridLayoutManager) {
                val params = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
                column = params.spanIndex
                fullSpan = params.isFullSpan
                mSpanCount = layoutManager.spanCount
            }
            position -= mStartFromSize
            if (mIncludeEdge) {
                /*
                 *eg
                 * spacing = 10 ；spanCount = 3
                 * ---------10--------
                 * 10   3+7   6+4    10
                 * ---------10--------
                 * 10   3+7   6+4    10
                 * ---------10--------
                 */
                if (fullSpan) {
                    outRect.left = 0
                    outRect.right = 0
                } else {
                    outRect.left = mSpacing - column * mSpacing / mSpanCount
                    outRect.right = (column + 1) * mSpacing / mSpanCount
                }
                if (spanGroupIndex > -1) {
                    if (spanGroupIndex < 1 && position < mSpanCount) {
                        outRect.top = mSpacing
                    }
                } else {
                    if (fullPosition == -1 && position < mSpanCount && fullSpan) {
                        fullPosition = position
                    }
                    val isFirstLineStagger =
                        (fullPosition == -1 || position < fullPosition) && position < mSpanCount
                    if (isFirstLineStagger) {
                        outRect.top = mSpacing
                    }
                }
                outRect.bottom = mSpacing
            } else {
                /*
                 *eg
                 * spacing = 10 ；spanCount = 3
                 * --------0--------
                 * 0   3+7   6+4    0
                 * -------10--------
                 * 0   3+7   6+4    0
                 * --------0--------
                 */
                if (fullSpan) {
                    outRect.left = 0
                    outRect.right = 0
                } else {
                    outRect.left = column * mSpacing / mSpanCount
                    outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount
                }
                if (spanGroupIndex > -1) {
                    if (spanGroupIndex >= 1) {
                        outRect.top = mSpacing
                    }
                } else {
                    if (fullPosition == -1 && position < mSpanCount && fullSpan) {
                        fullPosition = position
                    }
                    val isStaggerShowTop =
                        position >= mSpanCount || fullSpan && position != 0 || fullPosition != -1 && position != 0
                    if (isStaggerShowTop) {
                        outRect.top = mSpacing
                    }
                }
            }
        }
    }


    fun setStartFrom(startFromSize: Int): GridSpaceItemDecoration {
        mStartFromSize = startFromSize
        return this
    }


    fun setEndFromSize(endFromSize: Int): GridSpaceItemDecoration {
        mEndFromSize = endFromSize
        return this
    }


    fun setNoShowSpace(startFromSize: Int, endFromSize: Int): GridSpaceItemDecoration {
        mStartFromSize = startFromSize
        mEndFromSize = endFromSize
        return this
    }
}