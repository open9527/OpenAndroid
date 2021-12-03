package com.farmer.open9527.rmt.export.net.vo.base

import java.io.Serializable

open class PageQueryParam : Serializable {
    
    companion object {
        const val ORDER_BY_RELEASE_DESC = "release_desc" //发布排序
    }

    var pageNo: Int = 0
    var pageSize: Int = 0
    var orderBy: String? = null

    constructor(pageNo: Int, pageSize: Int) {
        this.pageNo = pageNo
        this.pageSize = pageSize
    }

    constructor(pageNo: Int, pageSize: Int, orderBy: String?) {
        this.pageNo = pageNo
        this.pageSize = pageSize
        this.orderBy = orderBy
    }


}