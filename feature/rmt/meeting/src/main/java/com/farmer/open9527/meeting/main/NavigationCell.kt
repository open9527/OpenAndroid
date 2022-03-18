package com.farmer.open9527.meeting.main

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.BR


class NavigationCell : BaseBindingCell<NavigationCell> {

    private var valueINavigationCell = ObservableField<INavigationCell>()

    var valueIndex = ObservableInt(2)
    var valueSelect = ObservableBoolean(false)
    var valueTitle = ObservableField<String>()
    var valueRes = ObservableInt()
    var valueSelectRes = ObservableInt()

    constructor(
        title: String,
        drawable: Int,
        selectDrawable: Int,
        index: Int,
        iNavigationCell: INavigationCell
    ) : super(R.layout.meeting__main_navigation__cell) {
        valueSelect.set(2 == index)
        valueIndex.set(index)
        valueTitle.set(title)
        valueRes.set(drawable)
        valueSelectRes.set(selectDrawable)
        valueINavigationCell.set(iNavigationCell)
    }


    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: NavigationCell) {
        val valueINavigationCell: INavigationCell? = cell.valueINavigationCell.get()
        valueINavigationCell?.onSwitchFragment(cell.valueIndex.get())

        if (selectedPosition.get() == cell.valueIndex.get()) {
            return
        }
        updateStatus(cell.getDataList()?.get(selectedPosition.get()) as NavigationCell, false)
        updateStatus(cell, true)
        selectedPosition.set(cell.valueIndex.get())

    }

    private fun updateStatus(cell: NavigationCell, boolean: Boolean) {
        cell.valueSelect.set(boolean)
        cell.updateCell(cell)
    }

    fun updateStatus(index: Int) {
//        if (index==selectedPosition.get()){
        val oldcell = getCell(selectedPosition.get())
        if (oldcell != null) {
            updateStatus(oldcell, false)
        }
        val cell = getCell(index)
        if (cell != null) {
            updateStatus(cell, true)
        }
        selectedPosition.set(index)

    }


    interface INavigationCell {
        fun onSwitchFragment(position: Int)
    }

    companion object {
        private var selectedPosition = ObservableInt(2)
    }
}