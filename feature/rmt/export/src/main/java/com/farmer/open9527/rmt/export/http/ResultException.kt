package com.farmer.open9527.rmt.export.http

import com.hjq.http.exception.HttpException

class ResultException : HttpException {
    val data: Any

    constructor(message: String?, data: Any) : super(message) {
        this.data = data
    }

    constructor(message: String?, cause: Throwable?, data: Any) : super(message, cause) {
        this.data = data
    }
}