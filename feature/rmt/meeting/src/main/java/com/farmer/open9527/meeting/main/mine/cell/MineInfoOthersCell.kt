package com.farmer.open9527.meeting.main.mine.cell

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class MineInfoOthersCell : BaseBindingCell<MineInfoOthersCell> {

    var valueJob = ObservableField<String>()
    var valueJobContent = ObservableField<String>()

    var valueRetinue = ObservableField<String>()
    var valueParty = ObservableField<String>()
    var valueRetinueContent = ObservableField<String>()


    constructor(
        job: String,
        jobContent: String,
        retinue: String,
        party: String,

        ) : super(R.layout.meeting__mine_info_others__cell) {
        valueJob.set(job)
        valueJobContent.set(jobContent)
        valueRetinue.set(retinue)
        valueParty.set(party)


    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: MineInfoOthersCell) {

    }

}