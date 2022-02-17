package com.farmer.open9527.share.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.farmer.open9527.share.QQ
import com.farmer.open9527.share.Sina
import com.farmer.open9527.share.WeChat
import com.farmer.open9527.share.core.model.ShareData
import com.farmer.open9527.share.core.model.ShareModel

object Share {

    private const val TAG = "Share"

    fun init(context: Context?, logEnable: Boolean, vararg shareDataList: ShareData) {
        ShareLogCatUtil.setLogCatInfo(logEnable, "(debug模式正式上线请关闭)")
        for (shareData in shareDataList) {
            when (shareData.shareModel) {
                ShareModel.QQ -> {
                    QQ.register(context, shareData.appId, shareData.appSecret, getFileProvider(context))
                    ShareLogCatUtil.i(TAG, "QQ register")
                }
                ShareModel.WECHAT -> {
                    WeChat.register(context, shareData.appId, shareData.appSecret)
                    ShareLogCatUtil.i(TAG, "WeChat register")
                }
                ShareModel.SINA -> {
                    Sina.register(context, shareData.appId, shareData.appSecret)
                    ShareLogCatUtil.i(TAG, "Sina register")
                }
                else -> {
                    ShareLogCatUtil.i(TAG, "not register")
                }
            }
        }
    }

    fun shareResultData(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
        QQ.shareResultData(activity, requestCode, resultCode, data)
        Sina.authorizeCallback(activity, requestCode, resultCode, data)
        Sina.doResultIntent(activity, data)
    }

    private fun getFileProvider(context: Context?): String {
        return context?.packageName + ".provider"
    }
}