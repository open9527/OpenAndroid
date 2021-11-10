package com.farmer.open9527.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.farmer.open9527.base.BaseActivity


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
abstract class CommonActivity : BaseActivity() {
    protected var mActivity: AppCompatActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = this
        super.onCreate(savedInstanceState)
        initStatusBar()
        initEvent()
        initView(savedInstanceState)
        initRequest()
    }

    open fun initStatusBar() {
        BarUtils.transparentStatusBar(this)
        BarUtils.setStatusBarLightMode(this, true)
    }

    open fun initEvent() {

    }

    open fun initView(bundle: Bundle?) {

    }

    open fun initRequest() {

    }

    open fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

}