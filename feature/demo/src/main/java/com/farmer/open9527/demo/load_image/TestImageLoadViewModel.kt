package com.farmer.open9527.demo.load_image

import androidx.annotation.RawRes
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.*
import com.farmer.open9527.demo.api.JsonApiUtils
import com.farmer.open9527.demo.api.vo.image.ImageVo
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
class TestImageLoadViewModel : ViewModel() {
    val valueTitle = ObservableField("ImageLoad")

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    val valueNoMoreData = ObservableBoolean(false)

    val valueIImageLoadCell = ObservableField<TestImageLoadCell.IImageLoadCell>()

    fun loadImages(@RawRes resId: Int) {
        viewModelScope.launch {
            getRawFileList(resId).forEach {
                val cell = TestImageLoadCell(it)
                cell.valueIImageLoadCell.set(valueIImageLoadCell.get())
                valueListData.add(cell)
            }
        }
    }


    private fun getRawFileList(@RawRes resId: Int): List<ImageVo> {
        val adapterSetting: Map<Type, Any> = JsonApiUtils.defaultAdapterSetting()
        val gson: Gson = JsonApiUtils.buildGson(adapterSetting)
        return gson.fromJson(
            ResourceUtils.readRaw2String(resId),
            object : TypeToken<List<ImageVo>>() {}.type
        )
    }
}