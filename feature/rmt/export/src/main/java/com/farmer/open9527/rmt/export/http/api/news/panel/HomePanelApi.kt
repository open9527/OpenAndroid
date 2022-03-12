package com.farmer.open9527.rmt.export.http.api.news.panel

import com.hjq.http.config.IRequestApi


class HomePanelApi : IRequestApi {

    override fun getApi(): String {
        return "/common/panel/home/list"
    }

}