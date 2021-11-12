package com.farmer.open9527.rmt.net.api.common

import com.farmer.open9527.flow.entity.ApiResponse
import com.farmer.open9527.rmt.net.api.BaseRepository


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
class CommonRepository : BaseRepository() {

    private val mService by lazy {
        CommonRetrofitClient.service
    }

    suspend fun requestHomePanelList(): ApiResponse<Any?> {
        return executeHttp {
            mService.requestHomePanelList()
        }
    }


}