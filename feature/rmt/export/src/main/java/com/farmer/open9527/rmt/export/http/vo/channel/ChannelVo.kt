package com.farmer.open9527.rmt.export.http.vo.channel

import com.farmer.open9527.rmt.export.http.vo.BaseVo
import com.farmer.open9527.rmt.export.http.vo.resource.AppTestVo
import com.farmer.open9527.rmt.export.http.vo.count.PosterCountVo
import com.farmer.open9527.rmt.export.http.vo.resource.ImageVo

class ChannelVo : BaseVo {

    var contentType: String? = null
    var title: String? = null
    var displayStyle: String? = null
    var children: List<ChannelVo>? = null

    constructor() : super() {}
    constructor(id: String?) : super(id) {}
}