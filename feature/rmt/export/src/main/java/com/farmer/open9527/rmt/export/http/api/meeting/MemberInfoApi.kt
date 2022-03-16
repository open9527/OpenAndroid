package com.farmer.open9527.rmt.export.http.api.meeting

import com.hjq.http.config.IRequestApi


class MemberInfoApi : IRequestApi {

    override fun getApi(): String {
        return "/api/app/memberInfo"
    }
}