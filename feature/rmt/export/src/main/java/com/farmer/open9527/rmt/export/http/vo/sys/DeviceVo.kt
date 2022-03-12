package com.farmer.open9527.rmt.export.http.vo.sys

import java.io.Serializable

class DeviceVo(
    var deviceId: String,
    var screenWidth: Int,
    var screenHeight: Int,
    var osVersion: VersionVo,
    var appVersion: VersionVo
) : Serializable