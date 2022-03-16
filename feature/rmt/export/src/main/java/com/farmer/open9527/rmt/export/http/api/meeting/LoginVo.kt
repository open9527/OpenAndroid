package com.farmer.open9527.rmt.export.http.api.meeting

import java.io.Serializable

open class LoginVo : Serializable {
    var token: String? = null
    
    constructor(token: String?) {
        this.token = token
    }
}