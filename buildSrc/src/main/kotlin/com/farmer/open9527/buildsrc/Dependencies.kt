package com.farmer.open9527.buildsrc

const val isRunAlone = false

/**
 * 版本信息
 */
object Versions {

    object Sdk {
        const val minSdk = 21
        const val targetSdk = 30
        const val compileSdk = 30
    }

    object App {
        const val versionCode = 100
        const val versionName = "1.0.0"
    }
}

object Configs {
    object Sign {
        const val keyAlias = "open9527"

        const val keyPassword = "open9527"

        const val keystore = "keystore.jks"

        const val storePassword = "open9527"
    }

    object App {
        const val applicationId = "com.farmer.open9527"

        const val appDebug = true
    }

}


/**
 *  插件信息
 */
object Plugins {

    object Gradle {
        private const val GradlePluginVersion = "7.0.0"

        // Gradle 插件版本说明：https://developer.android.google.cn/studio/releases/gradle-plugin.html#updating-plugin
        const val GradlePlugin = "com.android.tools.build:gradle:$GradlePluginVersion"
    }


    object Aop {
        private const val AspectjxPluginVersion = "2.0.10"

        // AOP 配置插件：https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
        const val AspectjxPlugin =
            "com.hujiang.aspectjx:gradle-android-plugin-aspectjx:$AspectjxPluginVersion"
    }


    object Kotlin {
        private const val KotlinPluginVersion = "1.5.30"
        const val KotlinPlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:$KotlinPluginVersion"
    }

}


/**
 *  引用Lib信息
 */
object Libs {

    private const val AndroidxVersion = "1.0.0"

    object Google {
        const val Material = "com.google.android.material:material:1.4.0"

        const val Gson = "com.google.code.gson:gson:2.8.9"


        object Exoplayer {
            private const val ExoplayerVersion = "2.14.0"

            const val ExoplayerCore =
                "com.google.android.exoplayer:exoplayer-core:$ExoplayerVersion"

            const val ExoplayerUi =
                "com.google.android.exoplayer:exoplayer-ui:$ExoplayerVersion"

            const val ExoplayerDash =
                "com.google.android.exoplayer:exoplayer-dash:$ExoplayerVersion"

            const val ExoplayerHls =
                "com.google.android.exoplayer:exoplayer-hls:$ExoplayerVersion"

            const val ExoplayerSmoothStreaming =
                "com.google.android.exoplayer:exoplayer-smoothstreaming:$ExoplayerVersion"

            const val ExoplayerRtsp =
                "com.google.android.exoplayer:exoplayer-rtsp:$ExoplayerVersion"

            const val ExoplayerRtmp =
                "com.google.android.exoplayer:exoplayer-rtmp:$ExoplayerVersion"

        }

    }

    object SquareUp {
        object OkHttp3 {
            const val OkHttp = "com.squareup.okhttp3:okhttp:4.1.0"
            const val OkHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.0"
        }

        object RetroFit2 {
            const val RetroFit = "com.squareup.retrofit2:retrofit:2.9.0"
            const val RetroFitConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
        }
    }

    object Androidx {
        const val Appcompat = "androidx.appcompat:appcompat:1.3.1"

        object Layout {
            const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

            const val RecyclerView = "androidx.recyclerview:recyclerview:1.2.0"

            const val Viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"

            const val CardView = "androidx.cardview:cardview:1.0.0"
        }


        object Lifecycle {
            private const val AndroidxLifecycleVersion = "2.2.0"


            const val LifecycleCommon =
                "androidx.lifecycle:lifecycle-common:$AndroidxLifecycleVersion"

            //包含了 ViewModel 和 LiveData
            const val LifecycleExtensions =
                "androidx.lifecycle:lifecycle-extensions:$AndroidxLifecycleVersion"

            //指明使用Lifecycles
            const val LifecycleRuntime =
                "androidx.lifecycle:lifecycle-runtime:$AndroidxLifecycleVersion"

            //指明使用ViewModel
            const val LifecycleViewModel =
                "androidx.lifecycle:lifecycle-viewmodel:$AndroidxLifecycleVersion"

            //指明使用LiveData
            const val LifecycleLiveData =
                "androidx.lifecycle:lifecycle-livedata:$AndroidxLifecycleVersion"

            //kotlin 使用
            object AnnotationProcessor {
                const val LifecycleCompiler =
                    "androidx.lifecycle:lifecycle-compiler:$AndroidxLifecycleVersion"
            }
        }

        object Kotlin {
            private const val KotlinLifecycleVersion = "2.2.0"

            const val ktx = "androidx.core:core-ktx:1.5.0"
            const val ViewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:$KotlinLifecycleVersion"

        }

    }


    object Glide {
        private const val GlideVersion = "4.12.0"
        private const val GlideTransformationsVersion = "4.3.0"

        const val Glide = "com.github.bumptech.glide:glide:$GlideVersion"

        const val GlideCompiler = "com.github.bumptech.glide:glide:$GlideVersion"

        const val GlideTransformations =
            "jp.wasabeef:glide-transformations:$GlideTransformationsVersion"

    }

    object Coil {
        private const val CoilVersion = "1.4.0"

        const val Coil = "io.coil-kt:coil:${CoilVersion}"
        const val CoilGif = "io.coil-kt:coil-gif:${CoilVersion}"
        const val CoilSvg = "io.coil-kt:coil-svg:${CoilVersion}"
        const val CoilVideo = "io.coil-kt:coil-video:${CoilVersion}"
    }


    object Sdk {


        object UM {
            private const val UMSdkVersion = "7.1.4"

            // UMShareSdk 必选 common, asms, share-core
            const val UMSdkCommon = "com.umeng.umsdk:common:9.3.6"

            const val UMSdkASms = "com.umeng.umsdk:asms:1.2.1"

            const val UMSdkShareCore = "com.umeng.umsdk:share-core:$UMSdkVersion"

            const val UMSdkShareBoard = "com.umeng.umsdk:share_board:$UMSdkVersion"

            const val UMSdkShareQQ = "com.umeng.umsdk:share-qq:$UMSdkVersion"

            const val UMSdkShareWx = "com.umeng.umsdk:share-wx:$UMSdkVersion"

            const val UMSdkShareWB = "com.umeng.umsdk:share-sina:$UMSdkVersion"
        }

        object Tencent {
            const val QQ = "com.tencent.tauth:qqopensdk:3.51.2"

            const val wx = "com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.6.5"

            const val TBS = "com.tencent.tbs.tbssdk:sdk:43993"

        }

        object Sina {
            const val WeiBo = "com.sina.weibo.sdk:core:10.10.0:openDefaultRelease@aar"
        }

    }

    object UI {

        object Refresh {
            private const val RefreshVersion = "2.0.3"

            const val RefreshKernel = "com.scwang.smart:refresh-layout-kernel:$RefreshVersion"

            const val RefreshHeaderClassics =
                "com.scwang.smart:refresh-header-classics:$RefreshVersion"

            const val RefreshFooterClassics =
                "com.scwang.smart:refresh-footer-classics:$RefreshVersion"

            const val RefreshHeaderMaterial =
                "com.scwang.smart:refresh-header-material:$RefreshVersion"
        }

        const val Banner = "com.github.zhpanvip:BannerViewPager:3.5.1"

        const val PhotoView = "com.github.chrisbanes:PhotoView:2.3.0"

    }

    object Utils {
        const val UtilCode = "com.blankj:utilcodex:1.30.5"

    }

}