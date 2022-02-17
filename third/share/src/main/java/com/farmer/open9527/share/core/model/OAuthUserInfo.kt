package com.farmer.open9527.share.core.model

import java.io.Serializable

class OAuthUserInfo : Serializable {

    var nickName: String? = null

    var sex: String? = null

    var headImgUrl: String? = null

    var userId: String? = null




    override fun toString(): String {
        return "OAuthUserInfo(nickName=$nickName, sex=$sex, headImgUrl=$headImgUrl, userId=$userId)"
    }


}