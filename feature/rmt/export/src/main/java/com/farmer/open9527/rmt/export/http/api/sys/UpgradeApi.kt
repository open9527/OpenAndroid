package com.farmer.open9527.rmt.export.http.api.sys

import com.farmer.open9527.rmt.export.http.vo.sys.DeviceVo
import com.farmer.open9527.rmt.export.http.vo.sys.VersionVo
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestCache
import com.hjq.http.model.CacheMode


class UpgradeApi : IRequestApi, IRequestCache {

    override fun getApi(): String {
        return "/sys/version"
    }

    override fun getCacheMode(): CacheMode {
        return CacheMode.NO_CACHE
    }

    override fun getCacheTime(): Long {
        return 0
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