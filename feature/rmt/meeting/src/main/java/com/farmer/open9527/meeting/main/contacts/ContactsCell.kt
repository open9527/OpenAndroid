package com.farmer.open9527.meeting.main.contacts

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class ContactsCell : BaseBindingCell<ContactsCell> {

    var valueTitle = ObservableField<String>()
    var valueRes = ObservableInt()
    var valueFolder = ObservableBoolean()

    constructor(
        title: String,
        drawable: Int,
        folder: Boolean,
    ) : super(R.layout.meeting__contacts__cell) {
        valueTitle.set(title)
        valueRes.set(drawable)
        valueFolder.set(folder)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: ContactsCell) {
        if (cell.valueFolder.get()){
            ContactsActivity.startContactsActivity(cell.valueTitle.get())
        }
    }

}