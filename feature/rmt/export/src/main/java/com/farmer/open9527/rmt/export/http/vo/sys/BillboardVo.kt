package com.farmer.open9527.rmt.export.http.vo.sys

import com.farmer.open9527.rmt.export.http.vo.resource.ResourceVo
import java.io.Serializable

class BillboardVo : Serializable {
    var type: String? = null
    var skipable: Boolean = false
    var resource: ResourceVo? = null
    var actionUrl: String? = null
    var skipTime = 0

    companion object {
        fun isImage(billboard: BillboardVo?): Boolean {
            return billboard != null && ResourceVo.CONTENT_TYPE_IMAGE == billboard.type
        }

        fun isVideo(billboard: BillboardVo?): Boolean {
            return billboard != null && ResourceVo.CONTENT_TYPE_VIDEO == billboard.type
        }
    }
}