package com.farmer.open9527.rmt.app

import android.app.Application
import com.farmer.open9527.common.application.CommonApplication
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
        fun initSdk(application: Application) {
            MMKV.initialize(application)
        }

    }
}