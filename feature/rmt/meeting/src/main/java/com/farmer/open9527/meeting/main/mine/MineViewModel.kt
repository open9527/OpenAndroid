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
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.mine.cell.MineContentCell
import com.farmer.open9527.meeting.main.mine.cell.MineHeaderCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.HttpRequestBody
import com.farmer.open9527.rmt.export.http.api.meeting.LoginApi
import com.farmer.open9527.rmt.export.http.api.meeting.MemberInfoApi
import com.farmer.open9527.rmt.export.http.api.meeting.MemberVo
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.PostRequest
import okhttp3.Call

class MineViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("我的")
    val valueShowBack = ObservableInt(View.INVISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()



    fun requestMemberInfo(request: PostRequest) {
        request.api(MemberInfoApi())
            .request(object : HttpCallback<HttpData<MemberVo?>>(this) {
                override fun onSucceed(data: HttpData<MemberVo?>) {
                    LogUtils.i(TAG, "requestMemberInfo :" + GsonUtils.toJson(data.getData()))
                    val memberVo = data.getData()
                    val departmentVo = memberVo?.departments?.get(0)
                    valueListData.add(
                        MineHeaderCell(
                            memberVo,
                            departmentVo, R.drawable.meeting_contacts_user__icon
                        )
                    )

                    valueListData.add(
                        MineContentCell(
                            StringUtils.getString(R.string.meeting__mine_setting),
                            R.drawable.meeting_mine_setting__icon
                        )
                    )
                }

                override fun onEnd(call: Call?) {

                }
            })
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}