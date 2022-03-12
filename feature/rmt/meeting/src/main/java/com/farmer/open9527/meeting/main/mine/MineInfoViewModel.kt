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
import com.farmer.open9527.meeting.main.mine.cell.MineInfoAvatarCell
import com.farmer.open9527.meeting.main.mine.cell.MineInfoContentCell
import com.farmer.open9527.meeting.main.mine.cell.MineInfoOthersCell
import com.farmer.open9527.meeting.main.mine.cell.MineLineCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener

class MineInfoViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("个人信息")
    val valueShowBack = ObservableInt(View.VISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    fun requestMsgData() {
        valueListData.clear()
        valueListData.add(MineLineCell())
        valueListData.add(
            MineInfoAvatarCell(
                StringUtils.getString(R.string.meeting__mine_info_avatar),
                R.drawable.meeting_contacts_user__icon
            )
        )
        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_name),
                "张三"
            )
        )

        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_gender),
                "男"
            )
        )

        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_alias),
                "无"
            )
        )
        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_unit),
                "市党代会"
            )
        )
        valueListData.add(MineLineCell())

        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_mobile),
                "15921208014"
            )
        )
        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_landline),
                "无"
            )
        )
        valueListData.add(MineLineCell())
        valueListData.add(
            MineInfoOthersCell(
                StringUtils.getString(R.string.meeting__mine_info_job),
                "无",
                StringUtils.getString(R.string.meeting__mine_info_retinue),
                "上海市/市党代会"
            )
        )
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