package com.farmer.open9527.meeting.main.mine.cell

import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class MineLineCell : BaseBindingCell<MineLineCell> {

    var valueHeight = ObservableInt(SizeUtils.dp2px(16f))
    var valueBgColor = ObservableInt(ColorUtils.getColor(R.color.transparent))

    constructor(
        height: Int,
        bgColor: Int,
    ) : super(R.layout.meeting__mine_line__cell) {
        valueHeight.set(height)
        valueBgColor.set(bgColor)

    }

    constructor() : super(R.layout.meeting__mine_line__cell) {
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }
}