package com.farmer.open9527.common.application

import com.farmer.open9527.base.BaseApplication
import com.farmer.open9527.common.R
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
abstract class CommonApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(android.R.color.transparent, R.color.common_text_color)
            ClassicsHeader(context)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            layout.setPrimaryColorsId(android.R.color.transparent, R.color.common_text_color)
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }
}