package com.farmer.open9527.buildsrc

object PluginsConfigs {

    object Plugins {
        const val Gradle = "com.android.tools.build:gradle:7.0.0"
        const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
        const val AspectJx = "com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10"

    }

    object Ids {
        const val Application = "com.android.application"
        const val Library = "com.android.library"
        const val Kotlin = "kotlin-android"
        const val KotlinKapt = "kotlin-kapt"

        const val Configs = "configs.gradle"
        const val CommonMaven = "maven.gradle"
    }

    object Maven {
        const val AliPlugin = "https://maven.aliyun.com/repository/gradle-plugin"
    }
}