package com.farmer.open9527.module.test.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class HeaderCell : BaseBindingCell<HeaderCell> {
    public val valueTitle = ObservableField<String>()

    constructor(title: String) : super(R.layout.cell_header) {
        valueTitle.set(title)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: HeaderCell) {
        LogUtils.i(TAG, "HeaderCell:onCellClick")
    }
}