package com.farmer.open9527.rmt.pkg.main


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.rmt.pkg.BR
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.databinding.RmtSplashViewBinding
import com.farmer.open9527.share.QQ
import com.farmer.open9527.share.WeChat
import com.farmer.open9527.share.core.Share


class RmtMainActivity : CommonActivity(), HandlerAction, ClickAction {

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
        setOnClickListener(R.id.tv_title)
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


    private fun share() {
//        WeChat.share("分享一个纯文本消息")
//        WeChat.shareImage(this,R.drawable.rmt__share__pic_large_01)
//        WeChat.shareImage(this,R.drawable.rmt__share__pic_large_02)
//        WeChat.shareImage(this,"https://mh.storage.shmedia.tech/b6d98775b937418fadd487a9c5f57119.jpg")
//        WeChat.shareWebPage(this,R.drawable.rmt__share__icon,"这是网页标题","这是网页描述","https://www.baidu.com/")
        WeChat.shareWebPage(
            this,
            "https://mh.storage.shmedia.tech/b6d98775b937418fadd487a9c5f57119.jpg",
            "这是网页标题",
            "这是网页描述",
            "https://www.baidu.com/"
        )
//        WeChat.shareVideo(this,R.drawable.rmt__share__icon,"这是视频标题","这是视频描述","https://mh.storage.shmedia.tech/34341b78f82148e289e462e41b50c389.mp4")

//        QQ.shareWebPage(
//            this,
//            "这是网页标题",
//            "这是网页描述",
//            "https://mh.storage.shmedia.tech/b6d98775b937418fadd487a9c5f57119.jpg",
//            "https://www.baidu.com/"
//        )

//        QQ.shareWebPage(
//            this,
//            "这是网页标题",
//            "这是网页描述",
//            "/storage/emulated/0/Pictures/save_2022-02-15_18-04-06.jpg",
//            "https://www.baidu.com/"
//        )
//        QQ.shareImageTextToQZone(
//            this,
//            "这是网页标题",
//            "这是网页描述",
//            "/storage/emulated/0/Pictures/save_2022-02-15_18-04-06.jpg",
//            "https://www.baidu.com/"
//        )
        //大图分享
//        QQ.shareImage(this, "/storage/emulated/0/微信图片_20220121160951.jpg")
//        QQ.shareImage(this, "/storage/emulated/0/Pictures/save_2022-02-15_18-04-06.jpg")

//        Sina.share(this, "这是分享文本", "", "", "", "")
    }

    private fun login() {
//        WeChat.login(this, "rmt_hk")
        QQ.login(this)
    }

    private fun logout() {
        QQ.logout(this)
    }

    private fun launchMiniProgram() {
        WeChat.launchMiniProgram(this, "gh_55e9b388e55b", "")
    }


    private fun unregister() {
        WeChat.unregister()
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_title -> {
                share()
//                login()
//                launchMiniProgram()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Share.shareResultData(this, requestCode, resultCode, data)
    }


    override fun onBackPressed() {
        if (mViewModel?.valueSplashPage?.get()!!) {
            return
        }

        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.i(TAG, "onDestroy")
        unregister()
        logout()
        removeCallbacks()
    }

}