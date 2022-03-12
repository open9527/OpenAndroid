package com.farmer.open9527.demo.share


import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.action.ClickAction
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.demo.R
import com.farmer.open9527.demo.BR
import com.farmer.open9527.demo.starter.ActionUtils
import com.farmer.open9527.demo.starter.BaseStarter
import com.farmer.open9527.share.QQ
import com.farmer.open9527.share.WeChat
import com.farmer.open9527.share.core.Share
import java.util.ArrayList


class ShareActivity : CommonActivity() , ClickAction {

    private var mViewModel: ShareViewModel? = null


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(ShareViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.test__share__activity, BR.vm, mViewModel!!)
    }

    override fun initStatusBar() {
//        super.initStatusBar()
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
//        findViewById<WebView>(R.id.web).loadUrl("https://cmweb.shmedia.tech/app_cm/app_shouye_meirituijian/20220106/4f68a6ff569b4826b402d51e14e43ee6.html")
        setOnClickListener(R.id.tv_title)

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
                toast("注意先配置Sdk的key以及包名")
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


    override fun onDestroy() {
        super.onDestroy()
        LogUtils.i(TAG, "onDestroy")
        unregister()
        logout()
    }


    class Starter : BaseStarter() {
        override fun name(serviceName: String): String {
            return PAGE_NAME
        }

        override fun usage(serviceName: String): String {
            return APP_SCHEMA + SERVICE
        }

        override fun supportService(): List<String> {
            val serviceList: MutableList<String> = ArrayList()
            serviceList.add(SERVICE)
            return serviceList
        }

        override fun startActivity(serviceName: String, queryParams: Map<String, String>?) {
            LogUtils.i("ShareActivity", "startActivity-queryParams:" + GsonUtils.toJson(queryParams))
            ActionUtils.startActivity(ShareActivity::class.java)
        }

        companion object {
            private const val SERVICE = "share_page"
            private const val PAGE_NAME = "跳转到\"ShareActivity\"页"
        }
    }
}