package com.farmer.open9527.meeting.main.mine

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
import com.farmer.open9527.meeting.main.mine.cell.MineContentCell
import com.farmer.open9527.meeting.main.mine.cell.MineHeaderCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener

class MineViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("我的")
    val valueShowBack = ObservableInt(View.INVISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    fun requestMsgData() {
        valueListData.clear()
        valueListData.add(
            MineHeaderCell("市党代会", "张三", R.drawable.meeting_contacts_user__icon)
        )
        valueListData.add(
            MineContentCell(
                StringUtils.getString(R.string.meeting__mine_setting),
                R.drawable.meeting_mine_setting__icon
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