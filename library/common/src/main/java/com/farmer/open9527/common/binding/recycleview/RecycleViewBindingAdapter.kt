package com.farmer.open9527.common.binding.recycleview

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
object RecycleViewBindingAdapter {
    @BindingAdapter(
        value = [
            "bindRecycleViewLayoutManager",
            "bindRecycleViewItemDecoration",
            "bindRecycleViewItemAnimator",
            "bindRecycleViewAdapter",
            "bindRecycleViewListData",
            "bindRecycleViewHasFixedSize",
            "bindRecycleViewHasAnim",
        ], requireAll = false
    )
    @JvmStatic
    fun setBindingRecycleView(
        recyclerView: RecyclerView?,
        layoutManager: RecyclerView.LayoutManager?,
        itemDecoration: RecyclerView.ItemDecoration?,
        itemAnimator: RecyclerView.ItemAnimator?,
        adapter: ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>?,
        list: List<BaseBindingCell<*>>?,
        hasFixedSize: Boolean,
        hasAnim: Boolean
    ) {
        if (recyclerView == null || adapter == null) return

        if (itemDecoration != null) {
            recyclerView.layoutManager = layoutManager
        }
        if (itemDecoration != null) {
            if (recyclerView.itemDecorationCount == 0) {
                recyclerView.addItemDecoration(itemDecoration)
            }
        }
        recyclerView.itemAnimator = itemAnimator
        recyclerView.setHasFixedSize(hasFixedSize)
        recyclerView.adapter = adapter
        if (list != null) {
            adapter.submitList(list)
        }
    }
}