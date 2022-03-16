package com.farmer.open9527.meeting.main.mine

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.common.event.SingleLiveEvent
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.login.LoginViewModel
import com.farmer.open9527.meeting.main.mine.cell.MineInfoAvatarCell
import com.farmer.open9527.meeting.main.mine.cell.MineInfoContentCell
import com.farmer.open9527.meeting.main.mine.cell.MineInfoOthersCell
import com.farmer.open9527.meeting.main.mine.cell.MineLineCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.HttpRequestBody
import com.farmer.open9527.rmt.export.http.api.meeting.LoginApi
import com.farmer.open9527.rmt.export.http.api.meeting.LoginVo
import com.farmer.open9527.rmt.export.http.api.meeting.MemberInfoApi
import com.farmer.open9527.rmt.export.http.api.meeting.MemberVo
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.PostRequest
import okhttp3.Call

class MineInfoViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("个人信息")
    val valueShowBack = ObservableInt(View.VISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()



    var valueName = ObservableField<String>()
    var valueDepartment = ObservableField<String>()
    var valueGender = ObservableField<String>()
    var valueMobile = ObservableField<String>()


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
                valueName.get()
            )
        )

        valueListData.add(
            MineInfoContentCell(
                StringUtils.getString(R.string.meeting__mine_info_gender),
                valueGender.get()
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
                valueMobile.get()
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
                valueDepartment.get(),
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