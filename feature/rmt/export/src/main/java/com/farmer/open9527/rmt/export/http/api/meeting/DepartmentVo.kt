package com.farmer.open9527.rmt.export.http.api.meeting

import java.io.Serializable

class DepartmentVo : Serializable {
    var id: String? = null
    var name: String? = null
    var parentId: String? = null
    var priority: String? = null
}