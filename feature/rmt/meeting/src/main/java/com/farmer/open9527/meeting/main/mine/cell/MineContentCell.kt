package com.farmer.open9527.meeting.main.mine.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.setting.SettingActivity
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class MineContentCell : BaseBindingCell<MineContentCell> {

    var valueTitle = ObservableField<String>()
    var valueRes = ObservableInt()

    constructor(
        title: String,
        drawable: Int,

        ) : super(R.layout.meeting__mine_content__cell) {
        valueTitle.set(title)
        valueRes.set(drawable)

    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: MineContentCell) {
        SettingActivity.startSettingActivity()
    }

}