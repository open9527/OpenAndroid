package com.farmer.open9527.demo.activity_result

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class TestResultApiViewModel : ViewModel() {
    val valueTitle = ObservableField("ResultApi")

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
//    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueAdapter =   ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

}