package com.farmer.open9527.module.test.vo.deserializer

import com.farmer.open9527.module.test.utils.JsonApiUtils
import com.farmer.open9527.module.test.vo.image.GifsVo
import com.farmer.open9527.module.test.vo.image.ImageVo
import com.farmer.open9527.module.test.vo.image.JpgVo
import com.google.gson.*
import java.lang.reflect.Type


/**
 *@author   open_9527
 *Create at 2021/11/10
 **/
class UrlJsonDeserializer() : JsonDeserializer<ImageVo> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ImageVo {
        val gson: Gson = JsonApiUtils.buildGsonExpectAdapter(ImageVo::class.java)
        val jsonObj = json?.asJsonObject
        if (jsonObj is JsonObject) {
            val urlElement = jsonObj.asJsonObject?.get("url")
            if (urlElement is JsonObject) {
                val regularElement = urlElement.asJsonObject?.get("regular")
                if (regularElement != null) {
                    return gson.fromJson(json, JpgVo::class.java)
                }
            }
        }

        return gson.fromJson(json, GifsVo::class.java)
    }


}