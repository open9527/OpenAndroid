package com.farmer.open9527.rmt.export.http

import com.hjq.http.config.IRequestServer
import com.hjq.http.model.CacheMode


class RequestServer(var hostUrl: String) : IRequestServer {

    override fun getHost(): String {
        return hostUrl
    }

    override fun getCacheMode(): CacheMode {
        return CacheMode.USE_CACHE_FIRST
    }

}