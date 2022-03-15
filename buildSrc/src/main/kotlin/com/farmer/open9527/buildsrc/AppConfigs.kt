package com.farmer.open9527.buildsrc

object AppConfigs {

    const val minSdk = 21
    const val targetSdk = 30
    const val compileSdk = 30

    const val applicationId = "com.farmer.open9527"
    const val versionCode = 100
    const val versionName = "1.0.0"
    const val logEnable = true
    const val hostUrl = "https://www.wanandroid.com"
    const val hostTestUrl = "https://www.wanandroid.com"
    const val qqId = ""
    const val qqSecret = ""
    const val wxId = ""
    const val wxSecret = ""


    //(0:rmt,1:meeting)
//    const val RMT_TYPE = 0
    const val RMT_TYPE = 1

    object Rmt {
        const val applicationId = "com.wdit.shrmt"
        const val versionCode = 203
        const val versionName = "2.0.3"
        const val logEnable = true
        const val hostUrl = "https://hard.shmedia.tech/media-basic-port/api/app"
        const val hostTestUrl = "http://k8s.shmedia.tech/media-basic-port/api/app"
        const val siteId = "100000"
        const val qqId = ""
        const val qqSecret = ""
        const val wxId = ""
        const val wxSecret = ""
    }


    object Meeting {
        const val applicationId = "com.wdit.meeting"
        const val versionCode = 100
        const val versionName = "1.0.0"
        const val logEnable = true
        const val hostUrl = "https://hard.shmedia.tech/media-basic-port/api/app"
        const val hostTestUrl = "http://k8s.shmedia.tech/media-basic-port/api/app"
        const val siteId = ""
        const val qqId = ""
        const val qqSecret = ""
        const val wxId = ""
        const val wxSecret = ""
    }
}