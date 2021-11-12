package com.farmer.open9527.rmt.net.api

import androidx.viewbinding.BuildConfig
import com.farmer.open9527.flow.entity.*
import kotlinx.coroutines.delay

/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
open class BaseRepository {

    suspend fun <T> executeHttp(block: suspend () -> ApiResponse<T>): ApiResponse<T> {
        //for test
        delay(500)
        runCatching {
            block.invoke()
        }.onSuccess { data: ApiResponse<T> ->
            return handleHttpOk(data)
        }.onFailure { e ->
            return handleHttpError(e)
        }
        return ApiEmptyResponse()
    }

    /**
     * 非后台返回错误，捕获到的异常
     */
    private fun <T> handleHttpError(e: Throwable): ApiErrorResponse<T> {
        if (BuildConfig.DEBUG) e.printStackTrace()
        return ApiErrorResponse(e)
    }

    /**
     * 返回200，但是还要判断isSuccess
     */
    private fun <T> handleHttpOk(data: ApiResponse<T>): ApiResponse<T> {
        return if (data.isSuccess) {
            getHttpSuccessResponse(data)
        } else {
            ApiFailedResponse(data.code, data.msg)
        }
    }

    /**
     * 成功和数据为空的处理
     */
    private fun <T> getHttpSuccessResponse(response: ApiResponse<T>): ApiResponse<T> {
        val data = response.data
        return if (data == null || data is List<*> && (data as List<*>).isEmpty()) {
            ApiEmptyResponse()
        } else {
            ApiSuccessResponse(data)
        }
    }

}