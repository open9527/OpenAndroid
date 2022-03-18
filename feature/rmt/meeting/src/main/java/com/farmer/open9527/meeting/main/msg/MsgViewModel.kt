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
            MsgCell("党代表群", "党的建设水平进一步提高。坚持党的全面领导", "8分钟前", R.drawable.meeting_msg_avatar_1__icon)
        )
        valueListData.add(
            MsgCell("市党代会", "您的物品已经配置完成，请及时至XXX领取。", "昨天", R.drawable.meeting_msg_avatar_2__icon)
        )

        valueListData.add(
            MsgCell("刘广会", "不断深化政治监管，推进巡查工作高质量发展", "昨天", R.drawable.meeting_msg_avatar_3__icon)
        )
        valueListData.add(
            MsgCell("许安", "加强清廉红桥建设，巩固深化基层作风", "星期三", R.drawable.meeting_msg_avatar_4__icon)
        )

        valueListData.add(
            MsgCell(
                "高亚瑞", "激活全面从严治党基层末梢，加强社区", "星期一", R.drawable.meeting_msg_avatar_5__icon
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