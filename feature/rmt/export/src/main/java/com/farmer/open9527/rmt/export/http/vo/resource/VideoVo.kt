package com.farmer.open9527.rmt.export.http.vo.resource


open class VideoVo : ResourceVo() {
    var orientation: String? = null
    var duration  = 0
    var width = 0
    var height = 0

    init {
        contentType = CONTENT_TYPE_VIDEO
    }
}