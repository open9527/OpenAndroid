package com.farmer.open9527.share

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import com.farmer.open9527.share.core.HttpUtils
import com.farmer.open9527.share.core.ShareLogCatUtil
import com.farmer.open9527.share.core.Util
import com.farmer.open9527.share.core.model.ShareContentType
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import org.json.JSONObject
import java.lang.Exception
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram


object WeChat {
    private const val TAG = "WeChat"

    private const val WX_APP_SCOPE = "snsapi_userinfo"
    private const val WX_APP_API = "https://api.weixin.qq.com"
    private const val WX_APP_ACCESS_TOKEN_API = "/sns/oauth2/access_token"
    private const val WX_APP_REFRESH_TOKEN_API = "/sns/oauth2/refresh_token"
    private const val WX_APP_USERINFO_API = "/sns/userinfo"

    private const val THUMB_SIZE = 150

    private var mWeChatApi: IWXAPI? = null
    private var mAppId: String = ""
    private var mAppSecret: String = ""

    private var mRefreshToken: String? = null
    private var mOpenId: String? = ""

    fun getWeChatApi(): IWXAPI? {
        return mWeChatApi
    }


    fun register(context: Context?, appId: String, appSecret: String) {
        mAppId = appId
        mAppSecret = appSecret
        mWeChatApi = WXAPIFactory.createWXAPI(context, appId, ShareLogCatUtil.DEBUG)
        mWeChatApi?.registerApp(appId)
    }

    fun registerWithReceiver(context: Context?, appId: String, appSecret: String) {
        mAppId = appId
        mAppSecret = appSecret
        mWeChatApi = WXAPIFactory.createWXAPI(context, appId, ShareLogCatUtil.DEBUG)
        context?.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                mWeChatApi?.registerApp(appId)
            }
        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
    }

    fun unregister() {
        mWeChatApi?.unregisterApp()
        mWeChatApi?.detach()
    }


    fun shareText(shareText: String?) {
        //初始化一个 WXTextObject 对象，填写分享的文本内容
        val textObj = WXTextObject()
        textObj.text = shareText

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        val msg = WXMediaMessage()
        msg.mediaObject = textObj
        msg.description = shareText

        val req = SendMessageToWX.Req()

        //transaction字段用与唯一标示一个请求
        req.transaction = System.currentTimeMillis().toString()
        req.message = msg

        //分享到对话:SendMessageToWX.Req.WXSceneSession
        //分享到朋友圈:SendMessageToWX.Req.WXSceneTimeline
        //分享到收藏:SendMessageToWX.Req.WXSceneFavorite

        req.scene = SendMessageToWX.Req.WXSceneSession

        //调用api接口，发送数据到微信
        mWeChatApi?.sendReq(req)
    }

    fun shareImage(context: Context?, drawableId: Int) {
        val bmp: Bitmap = BitmapFactory.decodeResource(context?.resources, drawableId)
        val imgObj = WXImageObject(bmp)
        val msg = WXMediaMessage()
        msg.mediaObject = imgObj

        //设置缩略图
        val thumbBmp: Bitmap = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true)
        bmp.recycle()
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

        //构造一个Req
        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("img")
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession
        mWeChatApi?.sendReq(req)

    }

    fun shareImage(context: Context?, url: String?) {
        Thread {
            val urlBmp: Bitmap = Util.getInternetPicture(url)
            val imgObj = WXImageObject(urlBmp)
            val msg = WXMediaMessage()
            msg.mediaObject = imgObj

            //设置缩略图
            val thumbBmp: Bitmap = Bitmap.createScaledBitmap(urlBmp, THUMB_SIZE, THUMB_SIZE, true)
            urlBmp.recycle()
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

            //构造一个Req
            val req = SendMessageToWX.Req()
            req.transaction = buildTransaction("img")
            req.message = msg
            req.scene = SendMessageToWX.Req.WXSceneSession
            mWeChatApi?.sendReq(req)
        }.start()
    }

    fun shareWebPage(
        context: Context?,
        drawableId: Int,
        title: String?,
        description: String?,
        webUrl: String?
    ) {
        //初始化一个WXWebpageObject，填写url
        val webpage = WXWebpageObject()
        webpage.webpageUrl = webUrl

        //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象

        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = description

        val bmp: Bitmap = BitmapFactory.decodeResource(context?.resources, drawableId)
        //设置缩略图
        val thumbBmp: Bitmap = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true)
        bmp.recycle()
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("webpage")
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession
        mWeChatApi?.sendReq(req)
    }

    fun shareWebPage(
        context: Context?,
        url: String?,
        title: String?,
        description: String?,
        webUrl: String?
    ) {
        Thread {
            //初始化一个WXWebpageObject，填写url
            val webpage = WXWebpageObject()
            webpage.webpageUrl = webUrl

            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象

            val msg = WXMediaMessage(webpage)
            msg.title = title
            msg.description = description

            val urlBmp: Bitmap = Util.getInternetPicture(url)
            //设置缩略图
            val thumbBmp: Bitmap = Bitmap.createScaledBitmap(urlBmp, THUMB_SIZE, THUMB_SIZE, true)
            urlBmp.recycle()
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

            val req = SendMessageToWX.Req()
            req.transaction = buildTransaction("webpage")
            req.message = msg
            req.scene = SendMessageToWX.Req.WXSceneSession
            mWeChatApi?.sendReq(req)
        }.start()
    }


    fun shareVideo(
        context: Context?,
        drawableId: Int,
        title: String?,
        description: String?,
        videoUrl: String?
    ) {

        //初始化一个WXVideoObject，填写url
        val video = WXVideoObject()
        video.videoUrl = videoUrl

        //用 WXVideoObject 对象初始化一个 WXMediaMessage 对象
        val msg = WXMediaMessage(video)
        msg.title = title
        msg.description = description
        val bmp: Bitmap = BitmapFactory.decodeResource(context?.resources, drawableId)
        //设置缩略图
        val thumbBmp: Bitmap = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true)
        bmp.recycle()
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

        //构造一个Req
        val req = SendMessageToWX.Req()
        req.transaction = buildTransaction("video")
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession
        mWeChatApi?.sendReq(req)
    }


    fun login(context: Context?, state: String?) {
        val req = SendAuth.Req()
        req.scope = WX_APP_SCOPE
        req.state = buildState(state)
        mWeChatApi?.sendReq(req)
    }

    fun launchMiniProgram(context: Context?, miniProgramId: String, miniProgramPath: String) {
        val req = WXLaunchMiniProgram.Req()
        req.userName = miniProgramId
        if (!TextUtils.isEmpty(miniProgramPath)) {
            req.path = miniProgramPath
        }
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE // 可选打开 开发版，体验版和正式版
        mWeChatApi?.sendReq(req)
    }


    fun getAccessToken(code: String) {
        //参数	是否必须	说明
        //appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
        //secret	是	应用密钥 AppSecret，在微信开放平台提交应用审核通过后获得
        //code	是	填写第一步获取的 code 参数
        //grant_type	是	填 authorization_code
        Thread {
            val params: MutableMap<String, String> = HashMap()
            params["appid"] = mAppId
            params["secret"] = mAppSecret
            params["code"] = code
            params["grant_type"] = "authorization_code"
            val data = HttpUtils.HttpGet(WX_APP_API + WX_APP_ACCESS_TOKEN_API, params)
            ShareLogCatUtil.i(TAG, "data=$data")

            try {
                val json = JSONObject(data)
                var accessToken = ""
                if (json.has("access_token")) {
                    accessToken = json.getString("access_token")
                }
                if (json.has("refresh_token")) {
                    mRefreshToken = json.getString("refresh_token")
                }
                if (json.has("openid")) {
                    mOpenId = json.getString("openid")
                }

                if (!TextUtils.isEmpty(accessToken) && (!TextUtils.isEmpty(mOpenId))) {
                    getUserinfo(accessToken, mOpenId!!)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }


    private fun getUserinfo(accessToken: String, openid: String) {
        //access_token	是	调用凭证
        //openid	是	普通用户的标识，对当前开发者帐号唯一
        //lang	否	国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为 en

        Thread {
            val params: MutableMap<String, String> = HashMap()
            params["access_token"] = accessToken
            params["openid"] = openid
            var data = HttpUtils.HttpGet(WX_APP_API + WX_APP_USERINFO_API, params)
            ShareLogCatUtil.i(TAG, "data=$data")
            try {
                val json = JSONObject(data)
                var headImgUrl = ""
                var nickname = ""
                if (json.has("headimgurl")) {
                    headImgUrl = json.getString("headimgurl")
                }
                if (json.has("nickname")) {
                    nickname = json.getString("nickname")
                }

                if (!TextUtils.isEmpty(headImgUrl) && (!TextUtils.isEmpty(nickname))) {
                    ShareLogCatUtil.i(TAG, "getUserinfo is ok")
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }.start()
    }

    fun refreshAccessToken() {
        //appid	是	应用唯一标识
        //grant_type	是	填 refresh_token
        //refresh_token	是	填写通过 access_token 获取到的 refresh_token 参数
        Thread {
            val params: MutableMap<String, String> = HashMap()
            params["appid"] = mAppId
            params["grant_type"] = "refresh_token"
            var result = HttpUtils.HttpGet(WX_APP_API + WX_APP_REFRESH_TOKEN_API, params)
            ShareLogCatUtil.i(TAG, "result=$result")
        }.start()
    }

    private fun buildTransaction(type: String?): String? {
        return if (type == null) System.currentTimeMillis().toString() else type + System.currentTimeMillis()
    }

    private fun buildState(state: String?): String? {
        return state ?: System.currentTimeMillis().toString()
    }

    private fun buildWXMediaMessage(contentType: ShareContentType?) {
        when (contentType) {
            ShareContentType.TEXT -> {
                val textObject = WXTextObject()
            }
            ShareContentType.IMAGE -> {
                val imageObject = WXImageObject()

            }
            ShareContentType.WEBPAGE -> {
                val webpageObject = WXWebpageObject()

            }
            ShareContentType.VIDEO -> {
                val webpageObject = WXVideoObject()

            }
            ShareContentType.MUSIC -> {
                val musicObject = WXMusicObject()

            }
            ShareContentType.MINI_PROGRAM -> {
                val miniProgramObject = WXMiniProgramObject()
            }
        }
    }

}