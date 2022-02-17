package com.farmer.open9527.share

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.farmer.open9527.share.core.ShareLogCatUtil
import com.tencent.connect.UserInfo
import com.tencent.connect.common.Constants
import com.tencent.connect.share.QQShare
import com.tencent.connect.share.QzoneShare
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import org.json.JSONObject
import java.io.File
import java.lang.Exception


object QQ {
    private const val TAG = "QQ"
    private const val QQ_APP_SCOPE = "all"

    private var mTencent: Tencent? = null

    fun getQQApi(): Tencent? {
        return mTencent
    }


    fun register(context: Context?,appId: String,appSecret: String, fileProvider: String?) {
        mTencent = Tencent.createInstance(appId, context, fileProvider)
    }


    fun shareWebPage(
        activity: Activity?,
        title: String?,
        description: String?,
        imageUrl: String?,
        webUrl: String?
    ) {
        val bundle = Bundle()
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT)
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE)
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title)
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, description)
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl)
//        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl)
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, webUrl)
        mTencent?.shareToQQ(activity, bundle, ShareUiListener(activity))
    }


    fun shareImageTextToQZone(
        activity: Activity?,
        title: String?,
        description: String?,
        imageUrl: String?,
        webUrl: String?
    ) {
        val bundle = Bundle()
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT)
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, title)
        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, description)
        bundle.putString(QzoneShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl)
//        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl)
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, webUrl)
        mTencent?.shareToQzone(activity, bundle, ShareUiListener(activity))
    }


    fun shareImage(
        activity: Activity?,
        localImageUrl: String?
    ) {
        val file = File(localImageUrl)
        if (file.length() >= 5 * 1024 * 1024) {
            ShareLogCatUtil.i(
                TAG,
                "code= " + Constants.ERROR_IMAGE_TOO_LARGE + " msg= " + Constants.MSG_SHARE_IMAGE_TOO_LARGE_ERROR
            )
            return
        }

        val bundle = Bundle()
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE)
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE)
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, localImageUrl)
        mTencent?.shareToQQ(activity, bundle, ShareUiListener(activity))
    }

    fun shareResultData(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
        Tencent.onActivityResultData(requestCode, resultCode, data, ShareUiListener(activity))
    }

    fun login(activity: Activity) {
        mTencent?.login(activity, QQ_APP_SCOPE, ShareUiListener(activity))
    }

    fun logout(context: Context?) {
        mTencent?.logout(context)
    }

    private fun getUserInfo(context: Context?, json: JSONObject) {
        try {
            var openId = ""
            var accessToken = ""
            var expiresIn = ""
            if (json.has("openid")) {
                openId = json.getString("openid")
            }
            if (json.has("access_token")) {
                accessToken = json.getString("access_token")
            }
            if (json.has("expires_in")) {
                expiresIn = json.getString("expires_in")
            }

            if (!TextUtils.isEmpty(accessToken) && (!TextUtils.isEmpty(openId))) {
                mTencent?.setAccessToken(accessToken, expiresIn)
                mTencent?.openId = openId
                val info = UserInfo(context, mTencent?.qqToken)
                info.getUserInfo(ShareUiListener(context))
                ShareLogCatUtil.i(TAG, "json= $json")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ShareLogCatUtil.e(TAG, "Exception= $e")
        }


    }

    private fun parseUserInfo(json: JSONObject) {

        try {
            var nickName = ""
            var gender = ""
            var headImgUrl = ""
            if (json.has("nickname")) {
                nickName = json.getString("nickname")
            }
            if (json.has("gender")) {
                gender = json.getString("gender")
            }
            if (json.has("figureurl_qq_1")) {
                headImgUrl = json.getString("figureurl_qq_1")
            }
            ShareLogCatUtil.i(TAG, "nickName=$nickName gender=$gender headImgUrl=$headImgUrl")
        } catch (e: Exception) {
            e.printStackTrace()
            ShareLogCatUtil.e(TAG, "parseUserInfo= $json")
        }
    }


    open class ShareUiListener(private val context: Context?) : IUiListener {
        override fun onComplete(response: Any?) {
            if (response == null) {
                ShareLogCatUtil.i(TAG, "response is null")
                return
            }
            val jsonResponse = response as JSONObject
            if (jsonResponse.length() == 0) {
                ShareLogCatUtil.i(TAG, "返回为空,操作失败")
                return
            }
            doComplete(response)
        }

        private fun doComplete(json: JSONObject) {
            //操作成功 处理分享,登陆,获取用户信息
            ShareLogCatUtil.i(TAG, "json= $json")
            getUserInfo(context, json)
            parseUserInfo(json)
        }


        override fun onError(error: UiError?) {
            ShareLogCatUtil.i(TAG, "msg=" + error?.errorMessage + "  Detail=" + error?.errorDetail)
        }

        override fun onCancel() {
            ShareLogCatUtil.i(TAG, "操作取消")
        }

        override fun onWarning(code: Int) {
            ShareLogCatUtil.i(TAG, "code=$code")
            when (code) {
                Constants.ERROR_NO_AUTHORITY -> {
                    ShareLogCatUtil.i(TAG, "请授权手Q访问分享的文件的读取权限!")
                }

            }
        }

    }
}