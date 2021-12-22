package com.farmer.open9527.demo.starter

import java.io.Serializable

class ActionVo : Serializable {

    var sample: String? = null
    var title: String? = null
    var serviceNameIndex: Int = 0
    var serviceNames: List<String>? = null
    var classPath: String? = null
    var desc: String? = null
    var authorize: Boolean? = false

    override fun toString(): String {
        return "ActionVo(sample=$sample, title=$title,  serviceNameIndex=$serviceNameIndex, serviceNames=$serviceNames, classPath=$classPath, desc=$desc, authorize=$authorize)"
    }


}