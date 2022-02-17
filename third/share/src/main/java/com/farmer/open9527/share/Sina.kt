package com.farmer.open9527.share

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.farmer.open9527.share.core.ShareLogCatUtil
import com.sina.weibo.sdk.api.TextObject
import com.sina.weibo.sdk.api.WeiboMultiMessage
import com.sina.weibo.sdk.auth.AuthInfo
import com.sina.weibo.sdk.auth.Oauth2AccessToken
import com.sina.weibo.sdk.auth.WbAuthListener
import com.sina.weibo.sdk.common.UiError
import com.sina.weibo.sdk.openapi.IWBAPI
import com.sina.weibo.sdk.openapi.SdkListener
import com.sina.weibo.sdk.openapi.WBAPIFactory
import com.sina.weibo.sdk.share.WbShareCallback


object Sina {
    private const val TAG = "Sina"



    private const val SINA_REDIRECT_URL = ""

    private const val SINA_APP_SCOPE = "all"


    private var mSinaApi: IWBAPI? = null

    fun getSinaApi(): IWBAPI? {
        return mSinaApi
    }


    fun register(context: Context?, appId: String, appSecret: String) {
        val authInfo = AuthInfo(context, appId, SINA_REDIRECT_URL, appSecret)
        mSinaApi = WBAPIFactory.createWBAPI(context)
        mSinaApi?.registerApp(context, authInfo, object : SdkListener {
            override fun onInitSuccess() {
                ShareLogCatUtil.i(TAG, "SDK初始化成功回调，成功⼀次后再次初始化将不再有任何回调")
            }

            override fun onInitFailure(e: Exception?) {
                ShareLogCatUtil.e(TAG, "SDK初始化失败回调 Exception=" + e?.message)
            }
        })
    }


    fun share(
        activity: Activity?,
        text: String,
        title: String?,
        description: String?,
        imageUrl: String?,
        webUrl: String?
    ) {
        val message = WeiboMultiMessage()
        val textObject = TextObject()
        var shareText = ""

        // 分享⽂字
        if (!TextUtils.isEmpty(text)) {
            shareText = text
            textObject.text = shareText
            message.textObject = textObject;
        }
        mSinaApi?.shareMessage(activity, message, true)

    }

    fun startAuth(activity: Activity?) {
        mSinaApi?.authorize(activity, AuthListener(activity))
    }

    fun startClientAuth(activity: Activity?) {
        mSinaApi?.authorizeClient(activity, AuthListener(activity))
    }

    fun startWebAuth(activity: Activity?) {
        mSinaApi?.authorizeWeb(activity, AuthListener(activity))
    }

    open class AuthListener(private val context: Context?) : WbAuthListener {
        override fun onComplete(token: Oauth2AccessToken?) {
            ShareLogCatUtil.i(TAG, "微博授权成功 token=" + token.toString())
        }

        override fun onError(error: UiError?) {
            ShareLogCatUtil.i(TAG, "微博授权失败 error=" + error.toString())
        }

        override fun onCancel() {
            ShareLogCatUtil.i(TAG, "微博授权取消")
        }

    }


    fun authorizeCallback(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
        mSinaApi?.authorizeCallback(activity, requestCode, resultCode, data)
    }

    fun doResultIntent(context: Context?, data: Intent?) {
        mSinaApi?.doResultIntent(data, ShareCallback(context))
    }

    open class ShareCallback(private val context: Context?) : WbShareCallback {
        override fun onComplete() {
            ShareLogCatUtil.i(TAG, "操作成功 ")
        }

        override fun onError(error: UiError?) {
            ShareLogCatUtil.i(TAG, "操作失败 error=" + error.toString())
        }

        override fun onCancel() {
            ShareLogCatUtil.i(TAG, "操作取消")
        }

    }

}