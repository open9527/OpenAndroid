package com.farmer.open9527.share.core.listenner

import com.farmer.open9527.share.core.model.ShareContentType


interface IShareContent {
    /**
     * @return 分享的方式
     */
    val type: ShareContentType

    /**
     * 分享的描述信息(摘要)
     */
    val summary: String?
        get() = null

    /**
     * 分享的标题
     */
    val title: String?
        get() = null

    /**
     * 获取跳转的链接
     */
    val uRL: String?
        get() = null

    /**
     * 分享的缩略图片
     */
    val thumbBmpBytes: ByteArray?
        get() = null

    /**
     * 分享的大图地址
     */
    val largeBmpPath: String?
        get() = null

    /**
     * 音频url
     */
    val musicUrl: String?
        get() = null

}