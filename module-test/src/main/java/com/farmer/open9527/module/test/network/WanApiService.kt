package com.farmer.open9527.module.test.network

import com.farmer.open9527.network.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *@author   open_9527
 *Create at 2021/10/18
 **/
interface WanApiService {

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }

    @GET("wxarticle/chapters/json")
    suspend fun getWxArticle(): ApiResponse<Any?>



    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): ApiResponse<Any?>


}