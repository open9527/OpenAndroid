package com.farmer.open9527.rmt.export.http.vo.sys

import java.io.Serializable

class VersionVo(
    var name: String,
    var version: String
) : Serializable {

    companion object {
        const val OS_NAME = "Android"
        const val APP_NAME = "Android"
    }
}