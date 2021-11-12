package com.farmer.open9527.launcher

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.rmt.ui.main.MainActivity
import kotlinx.coroutines.launch


/**
 *@author   open_9527
 *Create at 2021/11/12
 **/
class LauncherViewModel : ViewModel() {
    val valueTitle = ObservableField<String>()

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    val valueILauncherCell = ObservableField<LauncherCell.ILauncherCell>()


    fun loadLauncherCells() {
        viewModelScope.launch {
            valueListData.add(
                LauncherCell(
                    StringUtils.getString(R.string.launcher_title),
                    MainActivity::class.java,
                    valueILauncherCell.get()!!
                )
            )
        }
    }
}