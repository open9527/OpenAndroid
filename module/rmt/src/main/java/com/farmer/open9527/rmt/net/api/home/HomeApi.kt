package com.farmer.open9527.rmt.net.api.home

import com.farmer.open9527.flow.entity.ApiResponse
import retrofit2.http.POST


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
interface HomeApi {

    @POST("common/panel/home/list")
    suspend fun requestHomePanelList(): ApiResponse<Any?>

}