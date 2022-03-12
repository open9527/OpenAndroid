package com.farmer.open9527.rmt.export.http.vo.resource


class ImageVo() : ResourceVo() {
    var hueColor: String? = null
    var width = 0
    var height = 0

    constructor(sourceUrl: String?) : this() {
        this.sourceUrl = sourceUrl
    }

    init {
        contentType = CONTENT_TYPE_IMAGE
    }
}