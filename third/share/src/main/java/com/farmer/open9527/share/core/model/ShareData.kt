package com.farmer.open9527.share.core.model

data class ShareData(
    var shareModel: ShareModel,
    var appId: String,
    var appSecret: String,
    var appScope: String
) {
    override fun toString(): String {
        val stringBuffer = StringBuffer()
        stringBuffer.append("ShareData: ")
            .append("shareModel=").append(shareModel.toString()).append(" ")
            .append("appId=").append(appId.toString()).append(" ")
            .append("appSecret=").append(appSecret.toString()).append(" ")
            .append("appScope=").append(appScope.toString())

        return stringBuffer.toString()
    }

}
