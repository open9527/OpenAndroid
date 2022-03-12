package com.farmer.open9527.meeting.main.setting

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class SettingContentCell : BaseBindingCell<SettingContentCell> {

    var valueKey = ObservableField<String>()
    var valueContent = ObservableField<String>()

    constructor(
        key: String,
        value: String

    ) : super(R.layout.meeting__setting_content__cell) {
        valueKey.set(key)
        valueContent.set(value)


    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: SettingContentCell) {

    }

}