package com.farmer.open9527.rmt.export.net.vo.base

import java.io.Serializable


/**
 *@author   open_9527
 *Create at 2021/11/29
 **/
open class PageVo<T> : Serializable {

    var pageNo: Int = 0
    var pageSize: Int = 0
    var totalPage: Int = 0
    var totalCount: Int = 0

    var records: List<T>? = null
    var orderBy: String? = null

    constructor()
}