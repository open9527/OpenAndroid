package com.farmer.open9527.launcher

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ResourceUtils
import com.farmer.open9527.demo.api.JsonApiUtils
import com.farmer.open9527.demo.starter.ActionVo
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type


/**
 *@author   open_9527
 *Create at 2021/11/12
 **/
class LauncherViewModel : ViewModel() {
    val valueTitle = ObservableField<String>()

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val valueAdapter =  ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    val valueILauncherCell = ObservableField<LauncherCell.ILauncherCell>()

    fun loadLauncherCells() {
        viewModelScope.launch {
            val actionVos = getRawFileList()
            actionVos.forEach {
                valueListData.add(LauncherCell(it, valueILauncherCell.get()!!))
            }

        }
    }

    private fun getRawFileList(): List<ActionVo> {
        val adapterSetting: Map<Type, Any> = JsonApiUtils.defaultAdapterSetting()
        val gson: Gson = JsonApiUtils.buildGson(adapterSetting)
        return gson.fromJson(
            ResourceUtils.readRaw2String(R.raw.action),
            object : TypeToken<List<ActionVo>>() {}.type
        )
    }
}