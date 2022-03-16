package com.farmer.open9527.meeting.main.contacts

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
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.NavigationCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.api.meeting.MailListApi
import com.farmer.open9527.rmt.export.http.api.meeting.MailListVo
import com.farmer.open9527.rmt.export.http.api.news.panel.HomePanelApi
import com.farmer.open9527.rmt.export.http.vo.channel.ChannelVo
import com.hjq.gson.factory.GsonFactory
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.GetRequest
import com.hjq.http.request.PostRequest
import okhttp3.Call

class ContactsActivityViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("通讯录")
    val valueId = ObservableField("通讯录")
    val valueShowBack = ObservableInt(View.VISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    fun requestMsgData() {
//        valueListData.clear()
//        valueListData.add(
//            ContactsCell(
//                "张三",
//                R.drawable.meeting_contacts_user__icon,
//                false
//            )
//        )
//        valueListData.add(
//            ContactsCell(
//                "李四",
//                R.drawable.meeting_contacts_user__icon,
//                false
//            )
//        )
//        valueListData.add(
//            ContactsCell(
//                "市党代会",
//                R.drawable.meeting_contacts_folder__icon,
//                true
//            )
//        )
//
//        valueListData.add(
//            ContactsCell(
//                "十一届十三次全会",
//                R.drawable.meeting_contacts_folder__icon,
//                true
//            )
//        )
    }

    fun requestMailList(
        request: GetRequest,id: String?
    ) {
        request.api(MailListApi().apply {
            setDeptId(id)
        })
            .request(object : HttpCallback<HttpData<MailListVo?>>(this) {
                override fun onSucceed(data: HttpData<MailListVo?>) {
                    LogUtils.i(TAG, "requestMailList :" + GsonUtils.toJson(data.getData()))

                    val mailListVo = data.getData()
                    val memberVos = mailListVo?.memberVos
                    val departments = mailListVo?.departmentVos
                    if (memberVos != null) {
                        for (memberVo in memberVos) {
                            valueListData.add(
                                memberVo?.name?.let {
                                    ContactsCell(
                                        it,
                                        "",
                                        R.drawable.meeting_contacts_user__icon,
                                        false
                                    )
                                }
                            )
                        }
                    }

                    if (departments != null) {
                        for (departmentVo in departments) {
                            valueListData.add(
                                departmentVo?.name?.let {
                                    departmentVo.id?.let { it1 ->
                                        ContactsCell(
                                            it,
                                            it1,
                                            R.drawable.meeting_contacts_folder__icon,
                                            true
                                        )
                                    }
                                }
                            )
                        }
                    }

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