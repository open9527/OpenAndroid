package com.farmer.open9527.rmt.export.http.vo

import java.io.Serializable

open class BaseVo : Serializable {
    var id: String? = null
    var shareUrl: String? = null
    constructor() {}
    constructor(id: String?) {
        this.id = id
    }
    companion object {
        const val serialVersionUID = 1L
    }
}