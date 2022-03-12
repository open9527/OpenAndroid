package com.farmer.open9527.rmt.export.http.vo.resource

import com.farmer.open9527.rmt.export.http.vo.BaseVo

open class ResourceVo : BaseVo() {
    var contentType: String? = null
    var sourceUrl: String? = null
    var thumbUrl: String? = null
    var summary: String? = null

    companion object {
        const val CONTENT_TYPE_IMAGE = "image"
        const val CONTENT_TYPE_AUDIO = "audio"
        const val CONTENT_TYPE_VIDEO = "video"

        fun valueImage(resourceVo: ResourceVo): String? {
            return if (isVideo(resourceVo) || isAudio(resourceVo)) {
                resourceVo.thumbUrl
            } else {
                resourceVo.sourceUrl
            }
        }

        fun valueVideoUrl(resourceVo: ResourceVo): String? {
            return resourceVo.sourceUrl
        }

        fun isImage(resource: ResourceVo?): Boolean {
            return resource != null && CONTENT_TYPE_IMAGE == resource.contentType
        }

        fun isVideo(resource: ResourceVo?): Boolean {
            return resource != null && CONTENT_TYPE_VIDEO == resource.contentType
        }

        fun isAudio(resource: ResourceVo?): Boolean {
            return resource != null && CONTENT_TYPE_AUDIO == resource.contentType
        }
    }
}