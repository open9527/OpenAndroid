package com.farmer.open9527.rmt.export.http.vo

import java.io.Serializable

open class PageQueryParam @JvmOverloads constructor(
    var pageNo: Int = 1,
    var pageSize: Int = 10
) : Serializable {
    var orderBy: String? = null

    companion object {
        const val ORDER_BY_RELEASE_DESC = "release_desc" //发布排序
        const val ORDER_BY_RELEASE_ASC = "release_asc"
    }
}