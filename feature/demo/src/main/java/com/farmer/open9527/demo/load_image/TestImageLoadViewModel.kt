package com.farmer.open9527.demo.load_image

import android.view.View
import androidx.annotation.RawRes
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.*
import com.farmer.open9527.common.event.SingleLiveEvent
import com.farmer.open9527.demo.api.JsonApiUtils
import com.farmer.open9527.demo.api.vo.image.ImageVo
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.animator.BaseAnimation
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.refresh.IRefreshView
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
    val valueItemAnimator = ObservableField<RecyclerView.ItemAnimator>()
    val valueAnimation = ObservableField<BaseAnimation>()
    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()

//    private val cellList = ArrayList<BaseBindingCell<*>>()
//    val valueListData = ObservableField<List<BaseBindingCell<*>>>()

    var valueListData = ObservableArrayList<BaseBindingCell<*>>()

    val valueRefreshEvent = ObservableField<IRefreshView>()
    val valueNoMoreData = ObservableBoolean(false)

    val valueIImageLoadCell = ObservableField<TestImageLoadCell.IImageLoadCell>()

    fun loadImages(@RawRes resId: Int, page: Int) {
        viewModelScope.launch {
            if (page == 1) {
                valueListData.clear()
            }
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


    private var backClickEvent: SingleLiveEvent<View>? = null

    fun getBackClickEvent(): SingleLiveEvent<View>? {
        if (backClickEvent == null) {
            backClickEvent = SingleLiveEvent<View>()
        }
        return backClickEvent
    }


}