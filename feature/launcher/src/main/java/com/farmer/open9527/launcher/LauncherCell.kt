package com.farmer.open9527.launcher

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.demo.starter.ActionUtils
import com.farmer.open9527.demo.starter.ActionVo
import com.farmer.open9527.demo.starter.BaseStarter
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/12
 **/
class LauncherCell : BaseBindingCell<LauncherCell> {
    var valueTitle = ObservableField<String>()
    var valueSample = ObservableField<String>()
    private var valueILauncherCell = ObservableField<ILauncherCell>()

    constructor(
        actionVo: ActionVo?,
        iLauncherCell: ILauncherCell
    ) : super(R.layout.launcher__cell) {
        valueTitle.set(actionVo?.title)
        valueSample.set(actionVo?.sample)
        valueILauncherCell.set(iLauncherCell)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: LauncherCell) {
//        ActionUtils.jump(cell.valueSample.get() )
        ActionUtils.jumpJson(cell.valueSample.get())
    }

    interface ILauncherCell {
        fun launcherActivity(cls: Class<*>)
    }
}