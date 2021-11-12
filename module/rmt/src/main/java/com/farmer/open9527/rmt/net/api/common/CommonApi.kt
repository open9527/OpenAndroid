package com.farmer.open9527.rmt.net.api.common

import com.farmer.open9527.flow.entity.ApiResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
interface CommonApi {

    @POST("common/panel/home/list")
    suspend fun requestHomePanelList(): ApiResponse<Any?>

}