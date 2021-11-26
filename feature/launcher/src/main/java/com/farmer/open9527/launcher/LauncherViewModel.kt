package com.farmer.open9527.launcher

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.demo.activity_result.TestResultApiActivity
import com.farmer.open9527.demo.load_image.TestImageLoadActivity
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
                    StringUtils.getString(R.string.launch_module_rmt),
//                    MainActivity::class.java,
                    null,
                    valueILauncherCell.get()!!
                )
            )
            valueListData.add(
                LauncherCell(
                    StringUtils.getString(R.string.launch_module_test_image_load),
                    TestImageLoadActivity::class.java,
                    valueILauncherCell.get()!!
                )
            )
            valueListData.add(
                LauncherCell(
                    StringUtils.getString(R.string.launch_module_test_result_api),
                    TestResultApiActivity::class.java,
                    valueILauncherCell.get()!!
                )
            )
        }
    }
}