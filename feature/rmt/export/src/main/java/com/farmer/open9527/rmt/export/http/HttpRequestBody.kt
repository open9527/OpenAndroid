package com.farmer.open9527.rmt.export.http

import com.google.gson.GsonBuilder
import com.hjq.http.model.ContentType
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.IOException

class HttpRequestBody(obj: Any?) : RequestBody() {

    /**
     * Json 数据
     */
//    val json: String = GsonUtils.toJson(obj)
    val json: String = GsonBuilder().disableHtmlEscaping().create().toJson(obj)

    /**
     * 字节数组
     */
    private val mBytes: ByteArray = json.toByteArray()

    override fun contentType(): MediaType? {
        return ContentType.JSON
    }

    override fun contentLength(): Long {
        // 需要注意：这里需要用字节数组的长度来计算
        return mBytes.size.toLong()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        sink.write(mBytes, 0, mBytes.size)
    }

    override fun toString(): String {
        return json
    }

}