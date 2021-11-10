package com.farmer.open9527.module.test.viewmodel

import androidx.annotation.RawRes
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.*
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.cell.ImageLoadCell
import com.farmer.open9527.module.test.utils.JsonApiUtils
import com.farmer.open9527.module.test.vo.image.ImageVo
import com.farmer.open9527.module.test.vo.image.RawType
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class ImageLoadViewModel : ViewModel() {
    val valueTitle = ObservableField("ImageLoad")

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    fun loadImages(@RawRes  resId: Int) {
        viewModelScope.launch {
            getRawFileList(resId).forEach {
                valueListData.add(ImageLoadCell(it))
            }
        }
    }


    private fun getRawFileList(@RawRes  resId: Int): List<ImageVo> {
        val adapterSetting: Map<Type, Any> = JsonApiUtils.defaultAdapterSetting()
        val gson: Gson = JsonApiUtils.buildGson(adapterSetting)
        return gson.fromJson(
            ResourceUtils.readRaw2String(resId),
            object : TypeToken<List<ImageVo>>() {}.type
        )
    }
}