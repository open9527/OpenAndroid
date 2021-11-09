package com.farmer.open9527.recycleview.click

import android.view.View


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
interface IBaseCellClick<in CELL> :View.OnClickListener,View.OnLongClickListener {

    fun onCellClick(view: View, cell: CELL) {}

    fun onCellLongClick(view: View, cell: CELL): Boolean {

        return false
    }

}