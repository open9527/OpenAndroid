package com.farmer.open9527.network

import android.util.Log
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

/**
 *@author   open_9527
 *Create at 2021/10/18
 **/

enum class HttpError(var code: Int, var errorMsg: String) {
    TOKEN_EXPIRE(401, "token is expired"),
    PARAMS_ERROR(4003, "params is error")
    // ...... more
}

internal fun handlingApiExceptions(code: Int?, errorMsg: String?) = when (code) {
    HttpError.TOKEN_EXPIRE.code -> HttpLog(HttpError.TOKEN_EXPIRE.errorMsg)
    HttpError.PARAMS_ERROR.code -> HttpLog(HttpError.PARAMS_ERROR.errorMsg)
    else -> errorMsg?.let { HttpLog(it) }
}

internal fun handlingExceptions(e: Throwable) = when (e) {
    is HttpException -> HttpLog(e.message())

    is CancellationException -> {
    }
    is SocketTimeoutException -> {
    }
    is JsonParseException -> {
    }
    else -> {
    }
}

internal fun HttpLog(errorMsg: String) {
    Log.e("HttpError", errorMsg)
}