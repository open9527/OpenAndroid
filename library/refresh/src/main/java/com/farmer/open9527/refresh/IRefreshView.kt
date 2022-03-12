package com.farmer.open9527.refresh

import com.scwang.smart.refresh.layout.api.RefreshLayout


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
interface IRefreshView {
    
    fun onRefresh(refreshLayout: RefreshLayout?, hasRefresh: Boolean) {}

}