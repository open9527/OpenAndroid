package com.farmer.open9527.meeting.main.mine.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.mine.MineInfoActivity
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.export.http.api.meeting.DepartmentVo
import com.farmer.open9527.rmt.export.http.api.meeting.MemberVo

class MineHeaderCell : BaseBindingCell<MineHeaderCell> {

    var valueTitle = ObservableField<String>()
    var valueName = ObservableField<String>()
    private var valueGender = ObservableField<String>()
    var valueMobile = ObservableField<String>()
    var valueRes = ObservableInt()

    constructor(
        memberVo: MemberVo?,
        departmentVo: DepartmentVo?,
        drawable: Int
    ) : super(R.layout.meeting__mine_header__cell) {
        valueTitle.set(departmentVo?.name)
        valueName.set(memberVo?.name)
        valueMobile.set(memberVo?.mobile)
        valueRes.set(drawable)
        when (memberVo?.gender) {
            "1" -> {
                valueGender.set("男")
            }
            "0" -> {
                valueGender.set("女")
            }
            else -> {
                valueGender.set("无")
            }
        }
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: MineHeaderCell) {
        MineInfoActivity.startMineInfoActivity(
            cell.valueName.get(),
            cell.valueGender.get(),
            cell.valueTitle.get(),
            cell.valueMobile.get(),
            )
    }

}