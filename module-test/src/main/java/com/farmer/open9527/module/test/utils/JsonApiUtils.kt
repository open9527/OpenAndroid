package com.farmer.open9527.module.test.utils

import android.util.ArrayMap
import com.farmer.open9527.module.test.vo.deserializer.UrlJsonDeserializer
import com.farmer.open9527.module.test.vo.image.ImageVo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type


/**
 *@author   open_9527
 *Create at 2021/11/10
 **/
object JsonApiUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    fun defaultAdapterSetting(): Map<Type, Any> {
        val setting: MutableMap<Type, Any> = ArrayMap()
        setting[ImageVo::class.java] = UrlJsonDeserializer()
        return setting
    }


    fun buildGson(typeAdapterSetting: Map<Type, Any>): Gson {
        val builder: GsonBuilder = GsonBuilder()
            .setDateFormat(DATE_FORMAT)
        for (type in typeAdapterSetting.keys) {
            builder.registerTypeAdapter(type, typeAdapterSetting[type])
        }
        return builder.create()
    }


    fun buildGson(): Gson {
        return buildGson(defaultAdapterSetting())
    }

    fun buildGsonExpectAdapter(vararg types: Type): Gson {
        val typeAdapterSetting: MutableMap<Type, Any> = defaultAdapterSetting() as MutableMap<Type, Any>
        for (type in types) {
            typeAdapterSetting.remove(type)
        }
        return buildGson(typeAdapterSetting)
    }



}