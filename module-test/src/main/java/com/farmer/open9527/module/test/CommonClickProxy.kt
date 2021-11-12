package com.farmer.open9527.module.test

import android.view.View
import com.farmer.open9527.refresh.IRefreshView
import com.scwang.smart.refresh.layout.api.RefreshLayout


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
open class CommonClickProxy : View.OnClickListener, IRefreshView {

    override fun onClick(v: View?) {

    }

    override fun onRefresh(refreshLayout: RefreshLayout?, hasRefresh: Boolean) {

    }

    open fun onBack() {
    }
}