package com.farmer.open9527.flow.entity

import java.io.Serializable


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/

open class ApiResponse<T>(
    open val data: T? = null,
    open val code: Int? = null,
    open val msg: String? = null,
    open val error: Throwable? = null,
) : Serializable {val isSuccess: Boolean get() = code == 0}

data class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(data = response)

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiFailedResponse<T>(override val code: Int?, override val msg: String?) :
    ApiResponse<T>(code = code, msg = msg)

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>(error = throwable)