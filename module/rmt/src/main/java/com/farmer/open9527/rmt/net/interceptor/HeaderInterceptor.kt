package com.farmer.open9527.rmt.net.interceptor

import com.farmer.open9527.rmt.Configs
import okhttp3.Interceptor
import okhttp3.Response


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain
            .request()
            .newBuilder()
            .addHeader(HeaderKey.TOKEN.key, "")
            .addHeader(HeaderKey.SITE_ID.key, Configs.SITE_ID.value)
            .addHeader(HeaderKey.DEVICE_ID.key, Configs.DEVICE_ID.value)
        val response = chain.proceed(builder.build())
        val token = response.header(HeaderKey.TOKEN.key)
        return response
    }
}