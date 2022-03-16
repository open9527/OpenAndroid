package com.farmer.open9527.rmt.export.http.api.meeting

import com.hjq.http.config.IRequestApi


class LoginApi : IRequestApi {

    override fun getApi(): String {
        return "/api/app/login"
    }

    companion object {
        fun getRequestBody(
            userId: String,
            password: String

        ): LoginQueryParam {
            return LoginQueryParam(userId, password)
        }
    }
}