package com.farmer.open9527.rmt.app

object AppConfigs {
    const val SITE_ID = "siteId"


    fun getDebug(): Boolean {
        return BuildConfig.DEBUG
    }


    fun getPackageName(): String {
        return BuildConfig.APPLICATION_ID
    }


    fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getVersionCode(): Int {
        return BuildConfig.VERSION_CODE
    }


    fun getHostUrl(): String {
        return BuildConfig.HOST_URL
    }

    fun getHostTestUrl(): String {
        return BuildConfig.HOST_TEST_URL
    }

    fun getLogEnable(): Boolean {
        return BuildConfig.LOG_ENABLE
    }

    fun getSiteId(): String {
        return BuildConfig.SITE_ID
    }

//    fun getQQId(): String {
//        return BuildConfig.QQ_ID
//    }
//
//    fun getQQSecret(): String {
//        return BuildConfig.QQ_SECRET
//    }
//
//    fun getWXId(): String {
//        return BuildConfig.WX_ID
//    }
//
//    fun getWXSecret(): String {
//        return BuildConfig.WX_SECRET
//    }


}