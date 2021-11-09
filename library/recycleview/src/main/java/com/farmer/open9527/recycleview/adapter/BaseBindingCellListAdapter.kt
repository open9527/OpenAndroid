package com.farmer.open9527.recycleview.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell.Companion.onCreateBaseBindingCellViewHolder
import com.farmer.open9527.recycleview.diffutilcallbacks.DiffUtilCallbacks
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import java.util.ArrayList


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
open class BaseBindingCellListAdapter< CELL : BaseBindingCell<*>> :
    ListAdapter< CELL, BaseBindingCellViewHolder<ViewDataBinding>> {

    constructor() : super(DiffUtilCallbacks<CELL>().getCellItemCallback())

    constructor(diffCallback: DiffUtil.ItemCallback<CELL>) : super(diffCallback)

    constructor(config: AsyncDifferConfig<CELL>) : super(config)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBindingCellViewHolder<ViewDataBinding> {
        return onCreateBaseBindingCellViewHolder(parent,viewType)
    }

    override fun onBindViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        currentList[position].bindViewHolder(holder, position)

    }


    override fun getItemViewType(position: Int): Int {
        val cell = currentList[position]
        return cell.getViewType()
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].getItemId()
    }

    override fun onViewRecycled(holder: BaseBindingCellViewHolder<ViewDataBinding>) {
        super.onViewRecycled(holder)
        val position: Int = holder.bindingAdapterPosition
        if (position < 0 || position >= currentList.size) {
            return
        }
        currentList[position].onViewRecycled(holder, position)
    }

    override fun getCurrentList(): List<CELL> {
        return super.getCurrentList()
    }


    override fun submitList(list: List<CELL>?) {
        super.submitList(
            list
        ) {
            super.submitList(
                if (list == null) ArrayList() else ArrayList(
                    list
                )
            )
        }
    }

}