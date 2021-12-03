package com.farmer.open9527.rmt.export.net.vo.base

import java.io.Serializable


/**
 *@author   open_9527
 *Create at 2021/11/29
 **/
open class CountVo : Serializable {

    companion object {
        const val TARGET_TYPE_CONTENT = "content"//稿件
        const val TARGET_TYPE_COMMENT = "comment"//评论
        const val TARGET_TYPE_MOMENT = "moment"//动态
    }

    var targetType: String = ""
    var targetId: String = ""

    var readCount: Int = 0
    var read: Boolean = false

    var commentCount: Int = 0
    var comment: Boolean = false

    var likeCount: Int = 0
    var like: Boolean = false

    var favoriteCount: Int = 0
    var favorite: Boolean = false




}