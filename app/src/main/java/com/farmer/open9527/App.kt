package com.farmer.open9527

import android.app.Application
import com.farmer.open9527.common.crash.CrashHandler

/**
 * @author open_9527
 * Create at 2021/10/20
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        CrashHandler.register(this, BuildConfig.DEBUG)
    }
}