package com.farmer.open9527.imageload

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
import okhttp3.Dispatcher
import okhttp3.OkHttpClient


/**
 *@author   open_9527
 *Create at 2021/11/9
 *
 * DESC:Sample
 **/
class CoilApplication : Application(), ImageLoaderFactory {

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
                logger(DebugLogger(Log.VERBOSE))
            }
            .build()
    }

}

