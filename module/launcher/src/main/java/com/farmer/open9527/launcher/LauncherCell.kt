package com.farmer.open9527.launcher

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/12
 **/
class LauncherCell : BaseBindingCell<LauncherCell> {
    var valueTitle = ObservableField<String>()
    private var valueClass = ObservableField<Class<*>>()
    private var valueILauncherCell = ObservableField<ILauncherCell>()

    constructor(
        title: String,
        cls: Class<*>,
        iLauncherCell: ILauncherCell
    ) : super(R.layout.launcher__cell) {
        valueTitle.set(title)
        valueClass.set(cls)
        valueILauncherCell.set(iLauncherCell)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: LauncherCell) {
        cell.valueClass.get()?.let { valueILauncherCell.get()?.launcherActivity(it) }
    }

    interface ILauncherCell {
        fun launcherActivity(cls: Class<*>)
    }
}