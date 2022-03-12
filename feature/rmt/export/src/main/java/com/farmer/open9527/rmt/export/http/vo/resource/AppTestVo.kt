package com.farmer.open9527.rmt.export.http.vo.resource


/**
 * 测试使用 data class
 */
data class AppTestVo(var duration: Int = 0) : ResourceVo() {

    init {
        contentType = CONTENT_TYPE_AUDIO
    }

}
