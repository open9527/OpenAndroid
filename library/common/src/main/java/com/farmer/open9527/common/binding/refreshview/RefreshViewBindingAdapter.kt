package com.farmer.open9527.common.binding.refreshview

import androidx.databinding.BindingAdapter
import com.farmer.open9527.refresh.IRefreshView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
object RefreshViewBindingAdapter {

    @BindingAdapter(
        value =
        [
            "bindRefreshListener",
            "bindRefreshNoMoreData",
        ], requireAll = false
    )
    @JvmStatic
    fun setBindingRefreshView(
        refreshLayout: SmartRefreshLayout?,
        iRefreshView: IRefreshView?,
        noMoreData: Boolean
    ) {
        refreshLayout?.setNoMoreData(noMoreData)
        refreshLayout?.setEnableFooterFollowWhenNoMoreData(noMoreData)

        refreshLayout?.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                if (noMoreData) {
                    refreshLayout.finishLoadMoreWithNoMoreData()
                }
                iRefreshView?.onRefresh(refreshLayout, false)
                refreshLayout.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshLayout.resetNoMoreData()
                iRefreshView?.onRefresh(refreshLayout, true)
                refreshLayout.finishRefresh()
            }
        })
    }
}