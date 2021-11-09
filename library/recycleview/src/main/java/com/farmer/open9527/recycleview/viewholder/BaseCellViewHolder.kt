package com.farmer.open9527.recycleview.viewholder

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
open class BaseCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewArray = SparseArray<View>()

    fun <T : View?> findViewById(@IdRes viewId: Int): T? {
        var view = viewArray[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            viewArray.put(viewId, view)
        }
        return view as T
    }
}