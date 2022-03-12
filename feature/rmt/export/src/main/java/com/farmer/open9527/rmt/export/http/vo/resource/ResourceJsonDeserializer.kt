package com.farmer.open9527.rmt.export.http.vo.resource

import com.farmer.open9527.rmt.export.http.JsonApiUtils.buildGsonExpectAdapter
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ResourceJsonDeserializer : JsonDeserializer<ResourceVo> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ResourceVo {
        val gson = buildGsonExpectAdapter(ResourceVo::class.java)


        val contentTypeElement =
            json.asJsonObject["contentType"] ?: return gson.fromJson(json, ResourceVo::class.java)

        when (contentTypeElement.asString) {

            ResourceVo.CONTENT_TYPE_IMAGE -> {
                gson.fromJson(json, ImageVo::class.java)
            }
            ResourceVo.CONTENT_TYPE_VIDEO -> {
                gson.fromJson(json, VideoVo::class.java)
            }
            ResourceVo.CONTENT_TYPE_AUDIO -> {
                gson.fromJson(json, AudioVo::class.java)
            }
            else -> gson.fromJson(json, ResourceVo::class.java)
        }
        return gson.fromJson(json, ResourceVo::class.java)
    }
}