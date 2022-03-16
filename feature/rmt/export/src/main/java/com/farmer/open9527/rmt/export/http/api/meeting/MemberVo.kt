package com.farmer.open9527.rmt.export.http.api.meeting

import java.io.Serializable

class MemberVo : Serializable {
    var id: String? = null
    var userId: String? = null
    var name: String? = null
    var mobile: String? = null
    var gender: String? = null
    var departments: List<DepartmentVo?>? = null
}