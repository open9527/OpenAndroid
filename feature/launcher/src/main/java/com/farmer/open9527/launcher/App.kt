package com.farmer.open9527.launcher

import android.app.Application
import android.os.Build
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.util.CoilUtils
import coil.util.DebugLogger
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
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.net.Proxy


/**
 *@author   open_9527
 *Create at 2021/11/26
 **/
class App : CommonApplication(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initSdk(this)
    }

    companion object {
        private const val TAG = "App"

        fun initSdk(application: Application) {
            MMKV.initialize(application)
            initHttp(application)
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
//                .setServer(TestRequestServer(AppConfigs.getHostTestUrl()))
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
                        headers?.put("2333", "2333")
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


    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder(applicationContext))
                } else {
                    add(GifDecoder())
                }
                add(SvgDecoder(applicationContext))
                add(VideoFrameDecoder(applicationContext))
            }
            .okHttpClient {
                val dispatcher = Dispatcher().apply { maxRequestsPerHost = maxRequests }
                OkHttpClient.Builder()
                    .dispatcher(dispatcher)
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .crossfade(true)
            .apply {
                if (BuildConfig.DEBUG) {
                    logger(DebugLogger(Log.DEBUG))
                }
            }
            .build()
    }
}