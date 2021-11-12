package com.farmer.open9527.rmt.net.api

import androidx.viewbinding.BuildConfig
import com.farmer.open9527.rmt.net.interceptor.HeaderInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
abstract class BaseRetrofitClient {

    companion object CLIENT {
        private const val TIME_OUT = 5
    }

    private val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
            .connectionPool(ConnectionPool(5, 10, TimeUnit.SECONDS))
        handleBuilder(builder)
        builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

    abstract fun handleBuilder(builder: OkHttpClient.Builder)

    open fun <Service> getService(serviceClass: Class<Service>, baseUrl: String): Service {
        return Retrofit.Builder()
            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }
}