package com.farmer.open9527.rmt.export.http.vo.resource


class AudioVo : ResourceVo() {
    var duration = 0

    init {
        contentType = CONTENT_TYPE_AUDIO
    }
}