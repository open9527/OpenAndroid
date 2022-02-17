package com.farmer.open9527.share.core.model

enum class Platform(

    private val thirdParty: ShareModel?,
    private val packageName: String?
) {

    QQ(ShareModel.QQ, "com.tencent.mobileqq"),

    ZONE(ShareModel.QQ_ZONE, "com.tencent.mobileqq"),

    WECHAT(ShareModel.WECHAT, "com.tencent.mm"),

    CIRCLE(ShareModel.WECHAT_CIRCLE, "com.tencent.mm"),

    SINA(ShareModel.SINA, "com.sina.weibo");


    fun getThirdParty(): ShareModel? {
        return thirdParty
    }

    fun getPackageName(): String? {
        return packageName
    }

}