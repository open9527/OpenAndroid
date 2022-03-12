package com.farmer.open9527.rmt.app

import android.app.Application
import com.farmer.open9527.common.application.CommonApplication
import com.farmer.open9527.rmt.export.http.JsonApiUtils
import com.farmer.open9527.rmt.export.http.RequestHandler
import com.farmer.open9527.rmt.export.http.RequestServer
import com.hjq.gson.factory.GsonFactory
import com.hjq.http.EasyConfig
import com.hjq.http.EasyLog
import com.hjq.http.config.IRequestInterceptor
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import com.hjq.http.request.HttpRequest
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import java.net.Proxy

/**
 *@author   open_9527
 *Create at 2021/11/26
 **/
class App : CommonApplication() {
    override fun onCreate() {
        super.onCreate()
        initSdk(this)
    }

    companion object {
        private const val TAG = "App"
        fun initSdk(application: Application) {
            MMKV.initialize(application)
            initHttp(application)
//            initShareSdk(application)
        }

        private fun initHttp(application: Application) {
            // 网络请求框架初始化
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .build()

            EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(RequestServer(AppConfigs.getHostUrl()))
                // 设置请求处理策略
                .setHandler(RequestHandler(application))
                // 设置请求重试次数
                .setRetryCount(1)
                .setInterceptor(object : IRequestInterceptor {
                    override fun interceptArguments(
                        httpRequest: HttpRequest<*>?,
                        params: HttpParams?,
                        headers: HttpHeaders?
                    ) {
                        headers?.put(AppConfigs.SITE_ID, AppConfigs.getSiteId())
                        EasyLog.printJson(httpRequest, GsonFactory.getSingletonGson().toJson(params))
                        EasyLog.printJson(httpRequest, GsonFactory.getSingletonGson().toJson(headers))
                    }
                })
                .into()
            GsonFactory.setSingletonGson(JsonApiUtils.buildGson())
        }

//        private fun initShareSdk(application: Application) {
//            Share.init(
//                application,
//                AppConfigs.getLogEnable(),
//                ShareData(ShareModel.QQ, AppConfigs.getQQId(), AppConfigs.getQQSecret(), ""),
//                ShareData(ShareModel.WECHAT, AppConfigs.getWXId(), AppConfigs.getWXSecret(), ""),
//                ShareData(ShareModel.SINA, "xxx", "xxx", "")
//            )
//        }
    }

}