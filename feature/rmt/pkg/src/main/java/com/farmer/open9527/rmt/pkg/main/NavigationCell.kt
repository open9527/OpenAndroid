package com.farmer.open9527.rmt.pkg.main

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.BR


class NavigationCell : BaseBindingCell<NavigationCell> {

    private var valueINavigationCell = ObservableField<INavigationCell>()

    var valueIndex = ObservableInt(0)
    var valueSelect = ObservableBoolean(false)
    var valueTitle = ObservableField<String>()
    var valueRes = ObservableInt(R.drawable.main__home_off__icon)
    var valueSelectRes = ObservableInt(R.drawable.main__home_on__icon)

    constructor(
        title: String,
        drawable: Int,
        selectDrawable: Int,
        index: Int,
        iNavigationCell: INavigationCell
    ) : super(R.layout.rmt__main_navigation__cell) {
        valueSelect.set(0 == index)
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
        val oldcell = getCell(selectedPosition.get()) as NavigationCell
        val cell = getCell(index) as NavigationCell
        updateStatus(oldcell, false)
        updateStatus(cell, true)
        selectedPosition.set(index)
    }


    interface INavigationCell {
        fun onSwitchFragment(position: Int)
    }

    companion object {
        private var selectedPosition = ObservableInt(0)
    }
}