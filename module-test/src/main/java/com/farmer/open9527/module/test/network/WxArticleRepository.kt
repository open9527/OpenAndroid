package com.farmer.open9527.module.test.network

import com.farmer.open9527.network.ApiResponse
import com.farmer.open9527.network.BaseRepository

/**
 *@author   open_9527
 *Create at 2021/10/18
 **/
class WxArticleRepository : BaseRepository() {

    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun fetchWxArticleFromNet(): ApiResponse<Any?> {
        return executeHttp {
            mService.getWxArticle()
        }
    }


    suspend fun login(username: String, password: String): ApiResponse<Any?> {
        return executeHttp {
            mService.login(username, password)
        }
    }



}