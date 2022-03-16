package com.farmer.open9527.meeting.splash

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.SpanUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.main.MainActivity
import com.farmer.open9527.meeting.main.login.LoginActivity
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest

class SplashActivity : CommonActivity() {

    private lateinit var mViewModel: SplashViewModel

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(SplashViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__splash__activity, BR.vm, mViewModel)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun initTheme() {
        window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }


    override fun initView(bundle: Bundle?) {
        checkTaskRoot()
        super.initView(bundle)
        startMainActivity()
    }


    private fun checkTaskRoot() {
        // 问题及方案：https://www.cnblogs.com/net168/p/5722752.html
        // 如果当前 Activity 不是任务栈中的第一个 Activity
        if (!isTaskRoot) {
            val intent: Intent? = intent
            // 如果当前 Activity 是通过桌面图标启动进入的
            if (((intent != null) && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
                        && (Intent.ACTION_MAIN == intent.action))
            ) {
                // 对当前 Activity 执行销毁操作，避免重复实例化入口
                finish()
                return
            }
        }
    }

    open inner class ClickProxy {
        var onSkipClick = View.OnClickListener {
            LogUtils.i(TAG, "onSkipClick")
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        if (TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
            LoginActivity.startLoginActivity()
        } else {
            startActivity(MainActivity::class.java)
        }
        finish()
    }

    private fun skipSplash(skipTime: Int) {
        postDelayed({
            startMainActivity()
        }, skipTime * 1000L)
    }

    override fun onBackPressed() {
    }

    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }
}