package com.farmer.open9527.module.test.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.farmer.open9527.module.test.cell.EmptyCell
import com.farmer.open9527.module.test.cell.HeaderCell
import com.farmer.open9527.module.test.network.WxArticleRepository

import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager
import com.farmer.open9527.state.observer.StateLiveData
import kotlinx.coroutines.launch


/**
 *@author   open_9527
 *Create at 2021/10/20
 **/
class NetWorkViewModel : ViewModel() {


    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    private val repository by lazy { WxArticleRepository() }

    val userLiveData = StateLiveData<Any?>()
    val wxArticleLiveData = StateLiveData<Any?>()


    fun login(username: String, password: String) {
        viewModelScope.launch {
            userLiveData.value = repository.login(username, password)
        }
    }

    fun fetchWxArticleFromNet() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

    fun initRecycleView(
        layoutManager: WrapContentLinearLayoutManager,
        itemDecoration: RecyclerView.ItemDecoration
    ) {
        valueLayoutManager.set(layoutManager)
        valueItemDecoration.set(itemDecoration)
        valueAdapter.set(BaseBindingCellListAdapter())
        valueListData.add(HeaderCell("header"))
        valueListData.add(HeaderCell("header"))
        valueListData.add(HeaderCell("header"))
        valueListData.add(EmptyCell("empty"))
        valueListData.add(EmptyCell("empty"))
        valueListData.add(EmptyCell("empty"))
        valueListData.add(EmptyCell("empty"))
        valueListData.add(EmptyCell("empty"))
        valueListData.add(EmptyCell("empty"))
    }

}