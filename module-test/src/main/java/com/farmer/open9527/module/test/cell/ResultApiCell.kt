package com.farmer.open9527.module.test.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class ResultApiCell : BaseBindingCell<ResultApiCell> {
    val valueTitle = ObservableField("ResultApi")
    private val valueIResultApiCell = ObservableField<IResultApiCell>()

    constructor(title: String, iResultApiCell: IResultApiCell) : super(R.layout.result_api__cell) {
        valueTitle.set(title)
        valueIResultApiCell.set(iResultApiCell)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: ResultApiCell) {
        val iResultApiCell = valueIResultApiCell.get()
        iResultApiCell?.registerCallBack(cell.valueTitle.get())
    }

    interface IResultApiCell {
        fun registerCallBack(title: String?) {}
    }
}