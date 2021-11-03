package com.farmer.open9527.dialog

import android.util.SparseArray


/**
 *@author   open_9527
 *Create at 2021/11/3
 **/
class DialogDataBindingConfig {

    private val bindingParams = SparseArray<Any?>()

    constructor ()

    fun getBindingParams(): SparseArray<*> {
        return bindingParams
    }

    fun addBindingParam(
        variableId: Int,
        `object`: Any
    ): DialogDataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, `object`)
        }
        return this
    }

}