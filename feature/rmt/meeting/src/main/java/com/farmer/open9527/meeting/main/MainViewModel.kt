package com.farmer.open9527.meeting.main

import androidx.databinding.*
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener

class MainViewModel : ViewModel(), OnHttpListener<Any> {
    val valueTitle = ObservableField("重要会议")
    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueNavigationAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    val valueViewPager2Adapter = ObservableField<FragmentStateAdapter>()
    val valueViewPager2OffscreenPageLimit = ObservableInt()
    val valueViewPager2CurrentItem = ObservableInt()
    val valueViewPager2SmoothScroll = ObservableBoolean(false)
    val valueViewPager2IsUserInputEnabled = ObservableBoolean(false)
    val valueViewPager2PageChangeCallback = ObservableField<ViewPager2.OnPageChangeCallback>()


    fun requestNavigation(iNavigationCell: NavigationCell.INavigationCell) {
        valueListData.clear()
        valueListData.add(
            NavigationCell(
                "消息",
                R.drawable.meeting_tab_msg_def__icon,
                R.drawable.meeting_tab_msg__icon,
                0,
                iNavigationCell
            )
        )
        valueListData.add(
            NavigationCell(
                "通讯录",
                R.drawable.meeting_tab_contacts_def__icon,
                R.drawable.meeting_tab_contacts__icon,
                1,
                iNavigationCell
            )
        )
        valueListData.add(
            NavigationCell(
                "会议",
                R.drawable.meeting_tab_def__icon,
                R.drawable.meeting_tab__icon,
                2,
                iNavigationCell
            )
        )
        valueListData.add(
            NavigationCell(
                "我的",
                R.drawable.meeting_tab_mine_def__icon,
                R.drawable.meeting_tab_mine__icon,
                3,
                iNavigationCell
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