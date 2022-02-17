package com.farmer.open9527.rmt.app

import android.app.Application
import com.farmer.open9527.common.application.CommonApplication
import com.farmer.open9527.share.core.Share
import com.farmer.open9527.share.core.model.ShareData
import com.farmer.open9527.share.core.model.ShareModel
import com.tencent.mmkv.MMKV

/**
 *@author   open_9527
 *Create at 2021/11/26
 **/
class App : CommonApplication() {
    override fun onCreate() {
        super.onCreate()
        initSdk(this)
    }

    companion object {
        private const val TAG = "App"

        fun initSdk(application: Application) {
            MMKV.initialize(application)
            Share.init(
                application,
                BuildConfig.LOG_ENABLE,
                ShareData(ShareModel.QQ, BuildConfig.QQ_ID, BuildConfig.QQ_SECRET, ""),
                ShareData(ShareModel.WECHAT, BuildConfig.WX_ID, BuildConfig.WX_SECRET, ""),
                ShareData(ShareModel.SINA, "xxx", "xxx", "")
            )
        }

    }
}