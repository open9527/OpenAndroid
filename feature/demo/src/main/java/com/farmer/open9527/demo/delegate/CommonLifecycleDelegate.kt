package com.farmer.open9527.demo.delegate

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils

open class CommonLifecycleDelegate : LifecycleObserver {
    private var mActivity: AppCompatActivity? = null

    constructor(activity: AppCompatActivity?) {
        mActivity = activity
        addObserver(mActivity)
    }
    private fun addObserver(activity: AppCompatActivity?) {
        activity?.lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        LogUtils.i(javaClass.canonicalName, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mActivity = null
        LogUtils.i(javaClass.canonicalName, "onDestroy")
    }

}