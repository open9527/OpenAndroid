package com.farmer.open9527.rmt.export.http.vo.count

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class CountVo : Serializable {
    var targetType //content：稿件；comment：评论；moment：动态；
            : String? = null
    var targetId //目标ID
            : String? = null
    var readCount //阅读数，-1 表示隐藏阅读数
            : Int? = null
        get() = if (field == null) -1 else field

    @SerializedName("read")
    var read //当前用户是否已读
            : Boolean? = null
        get() = if (field == null) false else field
    var commentCount //评论数，-1 表示隐藏评论数
            : Int? = null
        get() = if (field == null) -1 else field

    @SerializedName("comment")
    var comment //当前用户是否已评论
            : Boolean? = null
        get() = if (field == null) false else field
    var likeCount //点赞数，-1 表示隐藏点赞数
            : Int? = null
        get() = if (field == null) -1 else field

    @SerializedName("like")
    var like //当前用户是否已赞
            : Boolean? = null
        get() = if (field == null) false else field

    @SerializedName("favorite")
    var favorite //当前用户是否已收藏
            : Boolean? = null
        get() = if (field == null) false else field
    var favoriteCount //收藏数，-1 表示隐藏收藏数
            : Int? = null
        get() = if (field == null) -1 else field

    companion object {
        private const val serialVersionUID = 1L
        fun updateReadStatus(source: CountVo?, target: CountVo?) {
            if (source != null && target != null) {
                target.readCount = source.readCount
                target.read = source.read
            }
        }

        fun updateCommentStatus(source: CountVo?, target: CountVo?) {
            if (source != null && target != null) {
                target.commentCount = source.commentCount
                target.comment = source.comment
            }
        }

        fun updateLikeStatus(source: CountVo?, target: CountVo?) {
            if (source != null && target != null) {
                target.likeCount = source.likeCount
                target.like = source.like
            }
        }

        fun updateFavorStatus(source: CountVo?, target: CountVo?) {
            if (source != null && target != null) {
                target.favorite = source.favorite
                target.favoriteCount = source.favoriteCount
            }
        }

        fun updateReadCount(count: CountVo?) {
            if (count != null && count.readCount!! >= 0) {
                count.readCount = count.readCount!! + 1
            }
        }
    }
}