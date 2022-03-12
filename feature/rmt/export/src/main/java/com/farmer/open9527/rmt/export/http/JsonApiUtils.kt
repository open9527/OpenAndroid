package com.farmer.open9527.rmt.export.http

import android.util.ArrayMap
import com.farmer.open9527.rmt.export.http.vo.resource.ResourceJsonDeserializer
import com.farmer.open9527.rmt.export.http.vo.resource.ResourceVo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

object JsonApiUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    private fun defaultAdapterSetting(): Map<Type, Any> {
        val setting: MutableMap<Type, Any> = ArrayMap()
        setting[ResourceVo::class.java] = ResourceJsonDeserializer()
        return setting
    }


    private fun buildGson(typeAdapterSetting: Map<Type, Any>): Gson {
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