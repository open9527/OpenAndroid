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
import com.farmer.open9527.share.core.Share
import com.farmer.open9527.share.core.model.ShareData
import com.farmer.open9527.share.core.model.ShareModel
import com.tencent.mmkv.MMKV
import okhttp3.Dispatcher
import okhttp3.OkHttpClient


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

            Share.init(
                application,
                BuildConfig.LOG_ENABLE,
                ShareData(ShareModel.QQ, BuildConfig.QQ_ID, BuildConfig.QQ_SECRET, ""),
                ShareData(ShareModel.WECHAT, BuildConfig.WX_ID, BuildConfig.WX_SECRET, ""),
                ShareData(ShareModel.SINA, "xxx", "xxx", "")
            )
        }

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