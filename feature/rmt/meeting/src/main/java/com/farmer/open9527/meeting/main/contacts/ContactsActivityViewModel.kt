package com.farmer.open9527.meeting.main.contacts

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.NavigationCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.api.news.panel.HomePanelApi
import com.farmer.open9527.rmt.export.http.vo.channel.ChannelVo
import com.hjq.gson.factory.GsonFactory
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.PostRequest

class ContactsActivityViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("通讯录")
    val valueShowBack = ObservableInt(View.VISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    fun requestMsgData() {
        valueListData.clear()
        valueListData.add(
            ContactsCell(
                "张三",
                R.drawable.meeting_contacts_user__icon,
                false
            )
        )
        valueListData.add(
            ContactsCell(
                "李四",
                R.drawable.meeting_contacts_user__icon,
                false
            )
        )
        valueListData.add(
            ContactsCell(
                "市党代会",
                R.drawable.meeting_contacts_folder__icon,
                true
            )
        )

        valueListData.add(
            ContactsCell(
                "十一届十三次全会",
                R.drawable.meeting_contacts_folder__icon,
                true
            )
        )
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}