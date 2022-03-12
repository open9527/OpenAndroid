package com.farmer.open9527.meeting.main.msg

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class MsgCell : BaseBindingCell<MsgCell> {

    var valueTitle = ObservableField<String>()
    var valueDesc = ObservableField<String>()
    var valueDate = ObservableField<String>()
    var valueRes = ObservableInt()

    constructor(
        title: String,
        desc: String,
        date: String,
        drawable: Int,
    ) : super(R.layout.meeting__msg__cell) {
        valueTitle.set(title)
        valueDesc.set(desc)
        valueDate.set(date)
        valueRes.set(drawable)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: MsgCell) {
        super.onCellClick(view, cell)
    }

}