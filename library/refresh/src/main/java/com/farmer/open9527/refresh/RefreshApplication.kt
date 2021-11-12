package com.farmer.open9527.refresh

import android.R
import android.app.Application
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
class RefreshApplication : Application() {

    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.white, R.color.white)
                ClassicsHeader(context)
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }
}