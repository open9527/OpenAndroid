package com.farmer.open9527.meeting.main.setting

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.mine.cell.MineLineCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener

class SettingViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("设置")
    val valueShowBack = ObservableInt(View.VISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    fun requestMsgData() {
        valueListData.clear()
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_account), ""))
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_notify), ""))
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_common), ""))
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_help), ""))
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_about), ""))
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_server), ""))
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_server), ""))
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_unit), "市党代会"))
        valueListData.add(MineLineCell())
        valueListData.add(SettingContentCell(StringUtils.getString(R.string.meeting__setting_permission), ""))
        valueListData.add(MineLineCell())
        valueListData.add(SettingLoginOutCell())
        valueListData.add(MineLineCell())
        valueListData.add(MineLineCell())
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}