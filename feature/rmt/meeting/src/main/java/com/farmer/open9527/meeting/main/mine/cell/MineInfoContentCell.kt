package com.farmer.open9527.meeting.main.mine.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class MineInfoContentCell : BaseBindingCell<MineInfoContentCell> {

    var valueKey = ObservableField<String>()
    var valueContent = ObservableField<String>()
    var valueMobileHint = ObservableInt(View.INVISIBLE)

    constructor(
        key: String,
        value: String

    ) : super(R.layout.meeting__mine_info_content__cell) {
        valueKey.set(key)
        valueContent.set(value)
        if (StringUtils.equals(StringUtils.getString(R.string.meeting__mine_info_mobile), key)) {
            valueMobileHint.set(View.VISIBLE)
        } else {
            valueMobileHint.set(View.INVISIBLE)
        }

    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: MineInfoContentCell) {

    }

}