package com.farmer.open9527.rmt.export.http.api.meeting

import java.io.Serializable

open class LoginQueryParam @JvmOverloads constructor(
    var userId: String,
    var password: String
) : Serializable {

}