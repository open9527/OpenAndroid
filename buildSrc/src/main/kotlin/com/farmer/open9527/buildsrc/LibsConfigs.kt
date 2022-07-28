package com.farmer.open9527.buildsrc

object LibsConfigs {

    const val Appcompat = "androidx.appcompat:appcompat:1.3.1"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val RecyclerView = "androidx.recyclerview:recyclerview:1.2.0"
    const val Viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val CardView = "androidx.cardview:cardview:1.0.0"
    const val Material = "com.google.android.material:material:1.4.0"

    private const val KotlinxCoroutinesVersion = "1.6.0"
    const val KotlinxCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${KotlinxCoroutinesVersion}"
    const val KotlinxCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${KotlinxCoroutinesVersion}"

    private const val lifecycleKtxVersion = "2.3.1"
    const val LifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycleKtxVersion}"
    const val LifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycleKtxVersion}"


    const val Gson = "com.google.code.gson:gson:2.8.9"
    const val OkHttp = "com.squareup.okhttp3:okhttp:4.1.0"
    const val OkHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.0"

    private const val Retrofit2Version = "2.9.0"
    const val Retrofit2 = "com.squareup.retrofit2:retrofit:${Retrofit2Version}"
    const val Retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Retrofit2Version}"


    const val Glide = "com.github.bumptech.glide:glide:4.12.0"
    const val GlideCompiler = "com.github.bumptech.glide:glide:4.12.0"
    const val GlideTransformations = "jp.wasabeef:glide-transformations:4.3.0"

    private const val CoilVersion = "1.4.0"
    const val Coil = "io.coil-kt:coil:${CoilVersion}"
    const val CoilGif = "io.coil-kt:coil-gif:${CoilVersion}"
    const val CoilSvg = "io.coil-kt:coil-svg:${CoilVersion}"
    const val CoilVideo = "io.coil-kt:coil-video:${CoilVersion}"

    private const val RefreshVersion = "2.0.3"
    const val RefreshKernel = "com.scwang.smart:refresh-layout-kernel:${RefreshVersion}"
    const val RefreshHeaderClassics = "com.scwang.smart:refresh-header-classics:${RefreshVersion}"
    const val RefreshFooterClassics = "com.scwang.smart:refresh-footer-classics:${RefreshVersion}"
    const val RefreshHeaderMaterial = "com.scwang.smart:refresh-header-material:${RefreshVersion}"


    private const val ExoplayerVersion = "2.14.0"
    const val ExoplayerCore = "com.google.android.exoplayer:exoplayer-core:$ExoplayerVersion"
    const val ExoplayerUi = "com.google.android.exoplayer:exoplayer-ui:$ExoplayerVersion"
    const val ExoplayerDash = "com.google.android.exoplayer:exoplayer-dash:$ExoplayerVersion"
    const val ExoplayerHls = "com.google.android.exoplayer:exoplayer-hls:$ExoplayerVersion"
    const val ExoplayerSmoothStreaming =
        "com.google.android.exoplayer:exoplayer-smoothstreaming:$ExoplayerVersion"
    const val ExoplayerRtsp = "com.google.android.exoplayer:exoplayer-rtsp:$ExoplayerVersion"
    const val ExoplayerRtmp = "com.google.android.exoplayer:exoplayer-rtmp:$ExoplayerVersion"


    object Sdk {
        const val QQ = "com.tencent.tauth:qqopensdk:3.51.2"
        const val WeChat = "com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.6.5"
        const val Sina = "io.github.sinaweibosdk:core:11.12.0@aar"
        const val TBS = "com.tencent.tbs:tbssdk:44165"
        const val QiNiu = "com.qiniu:qiniu-android-sdk:8.4.4"

    }

    const val MMKV = "com.tencent:mmkv-static:1.2.10"

    const val Banner = "com.github.zhpanvip:BannerViewPager:3.5.1"
    const val PhotoView = "com.github.chrisbanes:PhotoView:2.3.0"
    const val UtilCode = "com.blankj:utilcodex:1.31.0"

    const val EasyHttp = "com.github.getActivity:EasyHttp:10.8"
    const val GsonFactory = "com.github.getActivity:GsonFactory:5.2"
    const val XXPermissions = "com.github.getActivity:XXPermissions:13.2"

    object DKPlayer {
        private const val DKPlayerVersion = "3.3.5"

        // # 必选，内部默认使用系统mediaplayer进行解码
        const val Java = "xyz.doikki.android.dkplayer:dkplayer-java:$DKPlayerVersion"

        // # 可选，包含StandardVideoController的实现
        const val UI = "xyz.doikki.android.dkplayer:dkplayer-ui:$DKPlayerVersion"

        // #可选，使用exoplayer进行解码
        const val EXO = "xyz.doikki.android.dkplayer:player-exo:$DKPlayerVersion"

        // # 可选，使用ijkplayer进行解码
        const val IJK = "xyz.doikki.android.dkplayer:player-ijk:$DKPlayerVersion"

        // # 可选，如需要缓存或者抖音预加载功能请引入此库
        const val VideoCache = "xyz.doikki.android.dkplayer:videocache:$DKPlayerVersion"

    }
    //二维码
    //    implementation 'com.google.zxing:core:3.3.3'
    //    implementation('com.journeyapps:zxing-android-embedded:3.6.0') { transitive = false }


}