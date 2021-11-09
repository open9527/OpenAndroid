package com.farmer.open9527.recycleview.diffutilcallbacks

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.farmer.open9527.recycleview.cell.BaseBindingCell


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class DiffUtilCallbacks<CELL> {

    fun getCellItemCallback(): DiffUtil.ItemCallback<CELL> {
        return object : DiffUtil.ItemCallback<CELL>() {
            override fun areItemsTheSame(oldItem: CELL, newItem: CELL): Boolean {
                return if (oldItem == null || newItem == null) false
                else (oldItem as BaseBindingCell<*>).getUUID() == (newItem as BaseBindingCell<*>).getUUID()
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CELL, newItem: CELL): Boolean {
                return if (oldItem == null || newItem == null) false else oldItem == newItem
            }
        }
    }
}