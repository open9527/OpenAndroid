package com.farmer.open9527.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.farmer.open9527.base.BaseActivity
import com.farmer.open9527.common.action.*


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
abstract class CommonActivity : BaseActivity() , HandlerAction,ActivityAction, BundleAction,
    KeyboardAction,ToastAction {
    protected var mActivity: AppCompatActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = this
        initTheme()
        super.onCreate(savedInstanceState)
        initStatusBar()
        initEvent()
        initView(savedInstanceState)
        initRequest()
    }



    open fun initTheme() {

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

//    open fun startActivity(cls: Class<*>) {
//        startActivity(Intent(this, cls))
//    }

    /**
     * 初始化软键盘
     */
    protected open fun initSoftKeyboard() {
        // 点击外部隐藏软键盘，提升用户体验
        getContentView()?.setOnClickListener {
            // 隐藏软键，避免内存泄漏
            hideKeyboard(currentFocus)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }

    override fun finish() {
        super.finish()
        // 隐藏软键，避免内存泄漏
        hideKeyboard(currentFocus)
    }

    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent)
    }

    override fun getBundle(): Bundle? {
        return intent.extras
    }

    /**
     * 和 setContentView 对应的方法
     */
    open fun getContentView(): ViewGroup? {
        return findViewById(Window.ID_ANDROID_CONTENT)
    }

    override fun getContext(): Context {
        return this
    }

    override fun startActivity(intent: Intent) {
        return super<BaseActivity>.startActivity(intent)
    }


    @Suppress("deprecation")
    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        // 隐藏软键，避免内存泄漏
        hideKeyboard(currentFocus)
        // 查看源码得知 startActivity 最终也会调用 startActivityForResult
        super.startActivityForResult(intent, requestCode, options)
    }
}