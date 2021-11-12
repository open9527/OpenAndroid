package com.farmer.open9527.module.test.network

import com.farmer.open9527.state.base.BaseRetrofitClient
import okhttp3.OkHttpClient

/**
 *@author   open_9527
 *Create at 2021/10/18
 **/

object RetrofitClient : BaseRetrofitClient() {

    val service by lazy { getService(WanApiService::class.java, WanApiService.BASE_URL) }

    override fun handleBuilder(builder: OkHttpClient.Builder) = Unit

}