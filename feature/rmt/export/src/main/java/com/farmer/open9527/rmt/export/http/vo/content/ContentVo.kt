package com.farmer.open9527.rmt.export.http.vo.content

import com.farmer.open9527.rmt.export.http.vo.BaseVo
import com.farmer.open9527.rmt.export.http.vo.channel.ChannelVo
import com.farmer.open9527.rmt.export.http.vo.count.CountVo
import com.farmer.open9527.rmt.export.http.vo.resource.ResourceVo
import java.util.*

class ContentVo : BaseVo() {
    private val contentType: String? = null
    private val channel: ChannelVo? = null
    private val title: String? = null
    private val author: String? = null
    private val summary: String? = null
    private val displayDate: Date? = null
    private val displayStyle: String? = null
    private val displayResources: List<ResourceVo>? = null
    private val actionUrl: String? = null
    private val count: CountVo? = null
    private val countType: String? = null
}