package com.farmer.open9527.rmt.export.http.api.news

import com.farmer.open9527.rmt.export.http.vo.channel.ChannelVo
import com.farmer.open9527.rmt.export.http.vo.content.ContentQueryParam
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestCache
import com.hjq.http.model.CacheMode

class ContentListApi : IRequestApi {

    override fun getApi(): String {
        return "/news/content/list"
    }



    companion object {

        fun getRequestBody(
            channelId: String,
            orderBy: String,
            pageNo: Int,
            pageSize: Int
        ): ContentQueryParam {
            val contentQueryParam = ContentQueryParam()
            contentQueryParam.pageNo = pageNo
            contentQueryParam.pageSize = pageSize
            contentQueryParam.orderBy = orderBy
            contentQueryParam.channel = ChannelVo(channelId)
            return contentQueryParam
        }
    }

}