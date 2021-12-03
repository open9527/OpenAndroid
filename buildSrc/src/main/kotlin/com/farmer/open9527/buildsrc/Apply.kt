package com.farmer.open9527.buildsrc

object Apply {
    object Path {
        const val DEFAULT_PATH = "../"
        const val DEFAULT_MULTI_LEVEL_PATH = "../../"
    }

    object Plugin {
        const val LIBRARY_PLUGIN = "com.android.library"
        const val KOTLIN_PLUGIN = "kotlin-android"
        const val KOTLIN_KAPT_PLUGIN = "kotlin-kapt"
    }

    object From {
        const val APP_GRADLE = "app.gradle"
        const val COMMON_GRADLE = "common.gradle"
        const val FEATURE_APP_GRADLE = "feature_app.gradle"
        const val LAUNCHER_APP_GRADLE = "launcher_app.gradle"
    }
}