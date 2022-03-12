package com.farmer.open9527.launcher

object AppConfigs {

    fun getHostUrl(): String {
        return BuildConfig.HOST_URL
    }

    fun getHostTestUrl(): String {
        return BuildConfig.HOST_TEST_URL
    }

    fun getLogEnable(): Boolean {
        return BuildConfig.LOG_ENABLE
    }



    fun getQQId(): String {
        return BuildConfig.QQ_ID
    }

    fun getQQSecret(): String {
        return BuildConfig.QQ_SECRET
    }

    fun getWXId(): String {
        return BuildConfig.WX_ID
    }

    fun getWXSecret(): String {
        return BuildConfig.WX_SECRET
    }

}