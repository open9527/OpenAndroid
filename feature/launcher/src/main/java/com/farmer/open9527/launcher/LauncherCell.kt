package com.farmer.open9527.launcher

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.demo.starter.ActionUtils
import com.farmer.open9527.demo.starter.ActionVo
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
        //第一种方案 创建activity的静态内部内
//        ActionUtils.jump(cell.valueSample.get() )
        //第二种方案 配置json 实现
        ActionUtils.jumpJson(cell.valueSample.get())
    }

    interface ILauncherCell {
        fun launcherActivity(cls: Class<*>)
    }
}