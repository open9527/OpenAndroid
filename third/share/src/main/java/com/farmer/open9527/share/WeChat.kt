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
        //??????????????? WXTextObject ????????????????????????????????????
        val textObj = WXTextObject()
        textObj.text = shareText

        //??? WXTextObject ????????????????????? WXMediaMessage ??????
        val msg = WXMediaMessage()
        msg.mediaObject = textObj
        msg.description = shareText

        val req = SendMessageToWX.Req()

        //transaction????????????????????????????????????
        req.transaction = System.currentTimeMillis().toString()
        req.message = msg

        //???????????????:SendMessageToWX.Req.WXSceneSession
        //??????????????????:SendMessageToWX.Req.WXSceneTimeline
        //???????????????:SendMessageToWX.Req.WXSceneFavorite

        req.scene = SendMessageToWX.Req.WXSceneSession

        //??????api??????????????????????????????
        mWeChatApi?.sendReq(req)
    }

    fun shareImage(context: Context?, drawableId: Int) {
        val bmp: Bitmap = BitmapFactory.decodeResource(context?.resources, drawableId)
        val imgObj = WXImageObject(bmp)
        val msg = WXMediaMessage()
        msg.mediaObject = imgObj

        //???????????????
        val thumbBmp: Bitmap = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true)
        bmp.recycle()
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

        //????????????Req
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

            //???????????????
            val thumbBmp: Bitmap = Bitmap.createScaledBitmap(urlBmp, THUMB_SIZE, THUMB_SIZE, true)
            urlBmp.recycle()
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

            //????????????Req
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
        //???????????????WXWebpageObject?????????url
        val webpage = WXWebpageObject()
        webpage.webpageUrl = webUrl

        //??? WXWebpageObject ????????????????????? WXMediaMessage ??????

        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = description

        val bmp: Bitmap = BitmapFactory.decodeResource(context?.resources, drawableId)
        //???????????????
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
            //???????????????WXWebpageObject?????????url
            val webpage = WXWebpageObject()
            webpage.webpageUrl = webUrl

            //??? WXWebpageObject ????????????????????? WXMediaMessage ??????

            val msg = WXMediaMessage(webpage)
            msg.title = title
            msg.description = description

            val urlBmp: Bitmap = Util.getInternetPicture(url)
            //???????????????
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

        //???????????????WXVideoObject?????????url
        val video = WXVideoObject()
        video.videoUrl = videoUrl

        //??? WXVideoObject ????????????????????? WXMediaMessage ??????
        val msg = WXMediaMessage(video)
        msg.title = title
        msg.description = description
        val bmp: Bitmap = BitmapFactory.decodeResource(context?.resources, drawableId)
        //???????????????
        val thumbBmp: Bitmap = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true)
        bmp.recycle()
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true)

        //????????????Req
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
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE // ???????????? ?????????????????????????????????
        mWeChatApi?.sendReq(req)
    }


    fun getAccessToken(code: String) {
        //??????	????????????	??????
        //appid	???	???????????????????????????????????????????????????????????????????????????
        //secret	???	???????????? AppSecret?????????????????????????????????????????????????????????
        //code	???	???????????????????????? code ??????
        //grant_type	???	??? authorization_code
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
        //access_token	???	????????????
        //openid	???	??????????????????????????????????????????????????????
        //lang	???	???????????????????????????zh_CN ?????????zh_TW ?????????en ?????????????????? en

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
        //appid	???	??????????????????
        //grant_type	???	??? refresh_token
        //refresh_token	???	???????????? access_token ???????????? refresh_token ??????
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