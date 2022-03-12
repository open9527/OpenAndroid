package com.farmer.open9527.meeting.main.mine.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class MineInfoAvatarCell : BaseBindingCell<MineInfoAvatarCell> {

    var valueTitle = ObservableField<String>()
    var valueAvatar = ObservableInt()

    constructor(
        title: String,
        drawable: Int,

        ) : super(R.layout.meeting__mine_info_avatar__cell) {
        valueTitle.set(title)
        valueAvatar.set(drawable)

    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: MineInfoAvatarCell) {

    }

}