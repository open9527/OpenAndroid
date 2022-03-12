package com.farmer.open9527.rmt.export.http.vo.count

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PosterCountVo : Serializable {
    var targetType //目标类型。subscription：订阅号；tribe：圈子；topic：话题；account：个人；
            : String? = null
    var targetId //目标ID
            : String? = null
    var postCount //发布数
            : Int? = null
        get() = if (field == null) 0 else field
    var likeCount //获赞数
            : Int? = null
        get() = if (field == null) 0 else field
    var followCount //关注数,关注别人
            : Int? = null
        get() = if (field == null) 0 else field
    var fansCount //粉丝数，被别人关注
            : Int? = null
        get() = if (field == null) 0 else field
    var follow //follow: 正在关注; friend: 互相关注; unfollow: 未关注
            : String? = null

    @SerializedName("like")
    var like //当前用户是否已赞
            : Boolean? = null
        get() = if (field == null) false else field

    @SerializedName("favorite")
    var favorite //当前用户是否已收藏
            : Boolean? = null
        get() = if (field == null) false else field

    companion object {
        private const val FOLLOW_FOLLOW = "follow"
        private const val FOLLOW_FRIEND = "friend"
        private const val FOLLOW_UNFOLLOW = "unfollow"
        fun isFollow(count: PosterCountVo?): Boolean {
            return count != null && FOLLOW_FOLLOW == count.follow
        }

        fun isFollowAddFriend(count: PosterCountVo?): Boolean {
            return count != null && (FOLLOW_FOLLOW == count.follow || FOLLOW_FRIEND == count.follow)
        }

        fun isUnFollow(count: PosterCountVo?): Boolean {
            return count != null && FOLLOW_UNFOLLOW == count.follow
        }

        fun updateFollowStatus(source: PosterCountVo?, target: PosterCountVo?) {
            if (source != null && target != null) {
                target.fansCount = source.fansCount
                target.followCount = source.followCount
                target.follow = source.follow
            }
        }

        fun updateLikeStatus(source: PosterCountVo?, target: PosterCountVo?) {
            if (source != null && target != null) {
                target.likeCount = source.likeCount
                target.like = source.like
            }
        }

        fun updateFavorStatus(source: PosterCountVo?, target: PosterCountVo?) {
            if (source != null && target != null) {
                target.favorite = source.favorite
            }
        }
    }
}