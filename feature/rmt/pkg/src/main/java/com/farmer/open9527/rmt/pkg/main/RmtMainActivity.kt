package com.farmer.open9527.rmt.pkg.main

import android.os.Bundle
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.BR
import android.widget.RelativeLayout


import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.farmer.open9527.rmt.pkg.databinding.RmtSplashViewBinding
import android.view.ViewGroup
import android.webkit.WebView
import com.blankj.utilcode.util.BarUtils


class RmtMainActivity : CommonActivity(), HandlerAction {

    private var mViewModel: RmtMainViewModel? = null


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(RmtMainViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.rmt__main__activity, BR.vm, mViewModel!!)
    }

    override fun initStatusBar() {
//        super.initStatusBar()
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        addADContentView()
//        findViewById<WebView>(R.id.web).loadUrl("https://cmweb.shmedia.tech/app_cm/app_shouye_meirituijian/20220106/4f68a6ff569b4826b402d51e14e43ee6.html")
    }

    //动态配置广告业
    private fun addADContentView() {
        BarUtils.setNavBarVisibility(this, false)
        BarUtils.setStatusBarVisibility(this, false)
        val mLayoutInflater = LayoutInflater.from(this)
        val mView: View = mLayoutInflater.inflate(R.layout.rmt__splash__view, null)
        val mViewBinding = DataBindingUtil.bind<RmtSplashViewBinding>(mView)
        if (mViewBinding != null) {
            mViewBinding.lifecycleOwner = this
            mViewBinding.setVariable(BR.title, mViewModel?.valueSplashTitle?.get())
        }
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        addContentView(mView, layoutParams)
        postDelayed({
            (mView.parent as ViewGroup).removeView(mView)
            mViewModel?.valueSplashPage?.set(false)
            BarUtils.setNavBarVisibility(this, true)
            BarUtils.setStatusBarVisibility(this, true)
            BarUtils.transparentStatusBar(this)
            BarUtils.setStatusBarLightMode(this, true)
        }, 3000)

    }

    override fun onBackPressed() {
        if (mViewModel?.valueSplashPage?.get()!!) {
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }

}