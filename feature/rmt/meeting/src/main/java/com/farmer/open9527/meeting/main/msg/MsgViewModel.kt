package com.farmer.open9527.meeting.main.msg

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener

class MsgViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("消息")
    val valueShowBack = ObservableInt(View.INVISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    fun requestMsgData() {
        valueListData.clear()
        valueListData.add(
            MsgCell(
                "政务微信提醒",
                "企业微信支持添加超50万",
                "星期二",
                R.drawable.meeting_msg_wx__icon
            )
        )

        valueListData.add(
            MsgCell(
                "市党代会",
                " ",
                "星期日",
                R.drawable.meeting_msg__icon
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