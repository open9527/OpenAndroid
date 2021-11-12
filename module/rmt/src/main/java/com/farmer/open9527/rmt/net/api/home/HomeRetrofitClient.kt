package com.farmer.open9527.rmt.net.api.home

import com.farmer.open9527.rmt.Configs
import com.farmer.open9527.rmt.net.api.BaseRetrofitClient
import com.farmer.open9527.rmt.net.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient

/**
 *@author   open_9527
 *Create at 2021/10/18
 **/

object HomeRetrofitClient : BaseRetrofitClient() {

    val service by lazy {
        getService(
            HomeApi::class.java,
            Configs.BASE_URL.value + Configs.PATH_URL.value
        )
    }

    override fun handleBuilder(builder: OkHttpClient.Builder){

    }

}