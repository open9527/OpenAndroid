package com.farmer.open9527.rmt.export.http.vo

import java.io.Serializable
import kotlin.math.ceil

class PageVo<T> : Serializable {
    
    private val pageNo: Int = 0
    private val pageSize: Int = 0
    private val totalPage: Int = 0
    private val totalCount: Int = 0
    private val records: List<T>? = null


    /**
     * 判断是否是最后一页
     */
    fun isLastPage(): Boolean {
        return ceil((totalCount / pageSize).toFloat()) <= pageNo
    }
}