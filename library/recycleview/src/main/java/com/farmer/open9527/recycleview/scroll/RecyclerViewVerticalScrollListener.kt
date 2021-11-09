package com.farmer.open9527.recycleview.scroll

import androidx.recyclerview.widget.RecyclerView


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class RecyclerViewVerticalScrollListener : RecyclerView.OnScrollListener {

    private var mListener: IRecyclerViewVerticalScrollListener? = null

    constructor(listener: IRecyclerViewVerticalScrollListener?) {
        this.mListener = listener
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!recyclerView.canScrollVertically(-1)) {
            mListener?.onScrolledToTop()

        } else if (!recyclerView.canScrollVertically(1)) {
            mListener?.onScrolledToBottom()

        } else if (dy < 0) {
            mListener?.onScrolledUp()

        } else if (dy > 0) {
            mListener?.onScrolledDown()
        }
    }


    interface IRecyclerViewVerticalScrollListener {
        fun onScrolledUp() {}
        fun onScrolledDown() {}
        fun onScrolledToTop() {}
        fun onScrolledToBottom() {}
    }
}