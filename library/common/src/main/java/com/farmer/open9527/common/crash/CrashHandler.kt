package com.farmer.open9527.common.crash

import android.app.Application
import android.content.Context
import android.os.Process
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import kotlin.system.exitProcess


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class CrashHandler : Thread.UncaughtExceptionHandler {

    private val crashFileName = "crash_file"
    private val keyCrashTime = "key_crash_time"
    private val className = "com.android.internal.os"

    private var mApplication: Application? = null
    private var mDebug: Boolean = true

    private var mNextHandler: Thread.UncaughtExceptionHandler? = null

    constructor(application: Application, debug: Boolean) {
        mApplication = application
        mDebug=debug
        mNextHandler = Thread.getDefaultUncaughtExceptionHandler()
        check(javaClass.name != mNextHandler?.javaClass?.name) { "Please do not register twice !" }
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        val sharedPreferences =
            mApplication!!.getSharedPreferences(crashFileName, Context.MODE_PRIVATE)
        val currentCrashTime = System.currentTimeMillis()
        val lastCrashTime = sharedPreferences.getLong(keyCrashTime, 0)
        // 记录当前崩溃的时间，以便下次崩溃时进行比对
        sharedPreferences.edit().putLong(keyCrashTime, currentCrashTime).apply()


        // 致命异常标记：如果上次崩溃的时间距离当前崩溃小于 5 分钟，那么判定为致命异常
        val deadlyCrash = currentCrashTime - lastCrashTime < 1000 * 60 * 5

        if (mDebug) {
            LogUtils.i(
                "CrashHandler",
                " throwable: " + "class: " + throwable.javaClass.javaClass.name
            )
        } else {
            if (!deadlyCrash) {
                // 如果不是致命的异常就自动重启应用
                AppUtils.relaunchApp()
            }
        }

        // 不去触发系统的崩溃处理（com.android.internal.os.RuntimeInit$KillApplicationHandler）
        if (mNextHandler != null && !mNextHandler!!.javaClass.name.startsWith(className)) {
            mNextHandler!!.uncaughtException(thread, throwable)
        }

        // 杀死进程（这个事应该是系统干的，但是它会多弹出一个崩溃对话框，所以需要我们自己手动杀死进程）
        Process.killProcess(Process.myPid())
        exitProcess(10)
    }

    companion object {
        fun register(application: Application, debug: Boolean) {
            Thread.setDefaultUncaughtExceptionHandler(
                CrashHandler(application,debug)
            )
        }
    }
}