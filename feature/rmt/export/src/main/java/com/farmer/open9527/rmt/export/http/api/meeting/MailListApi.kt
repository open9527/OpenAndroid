package com.farmer.open9527.rmt.export.http.api.meeting

import com.hjq.http.config.IRequestApi


class MailListApi : IRequestApi {

    override fun getApi(): String {
        return "/api/app/mailList"
    }


    private var deptId: String? = null

    fun setDeptId(deptId: String?): MailListApi = apply {
        this.deptId = deptId
    }
}