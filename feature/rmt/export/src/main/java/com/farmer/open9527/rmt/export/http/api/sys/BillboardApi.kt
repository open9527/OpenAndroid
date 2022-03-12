package com.farmer.open9527.rmt.export.http.api.sys

import com.farmer.open9527.rmt.export.http.vo.sys.DeviceVo
import com.farmer.open9527.rmt.export.http.vo.sys.VersionVo
import com.hjq.http.config.IRequestApi


class BillboardApi : IRequestApi {

    override fun getApi(): String {
        return "/sys/billboard/list"
    }

    companion object {

        fun getRequestBody(
            deviceId: String,
            screenWidth: Int,
            screenHeight: Int,
            osVersion: String,
            appVersion: String
        ): DeviceVo {
            return DeviceVo(
                deviceId,
                screenWidth,
                screenHeight,
                VersionVo(VersionVo.OS_NAME, osVersion),
                VersionVo(VersionVo.APP_NAME, appVersion)
            )
        }
    }
}