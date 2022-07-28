package com.farmer.open9527.rmt.pkg.main

import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.*
import androidx.recyclerview.widget.ListAdapter
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.BR


class NavigationCell : BaseBindingCell<NavigationCell> {

    private var valueINavigationCell = ObservableField<INavigationCell>()

    var valueIndex = ObservableInt(2)
    var valueSelect = ObservableBoolean(false)
    var valueTitle = ObservableField<String>()
    var valueRes = ObservableInt(R.drawable.main__home_off__icon)
    var valueSelectRes = ObservableInt(R.drawable.main__home_on__icon)

    var valueResHight = ObservableInt(SizeUtils.dp2px(24f))

    constructor(
        title: String,
        drawable: Int,
        selectDrawable: Int,
        index: Int,
        iNavigationCell: INavigationCell

    ) : super(R.layout.rmt__main_navigation__cell) {
        valueSelect.set(2 == index)
        valueIndex.set(index)
        valueTitle.set(title)
        valueRes.set(drawable)
        valueSelectRes.set(selectDrawable)
        valueINavigationCell.set(iNavigationCell)
    }

    constructor(
        title: String,
        drawable: Int,
        selectDrawable: Int,
        index: Int,
        iNavigationCell: INavigationCell,
        @LayoutRes layoutId: Int
    ) : super(layoutId) {
        valueSelect.set(0 == index)
        valueIndex.set(index)
        valueTitle.set(title)
        valueRes.set(drawable)
        valueSelectRes.set(selectDrawable)
        valueINavigationCell.set(iNavigationCell)
        valueResHight.set(SizeUtils.dp2px(35f))
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

    fun updateStatus(adapter: ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>?, oldCell: NavigationCell, index: Int) {
        val newCell = adapter?.currentList?.get(index) as NavigationCell
        updateStatus(newCell, false)
        updateStatus(oldCell, true)
        selectedPosition.set(index)
    }


    interface INavigationCell {
        fun onSwitchFragment(position: Int)
    }

    companion object {
        private var selectedPosition = ObservableInt(2)
    }
}