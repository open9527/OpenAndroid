package com.farmer.open9527.base.page

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 *@author   open_9527
 *Create at 2021/5/18
 **/
class DataBindingConfig {
    private val bindingParams = SparseArray<Any?>()
    fun getBindingParams(): SparseArray<*> {
        return bindingParams
    }
    var layout: Int= 0
    var vmVariableId: Int = 0
    var stateViewModel: ViewModel? = null

    constructor(layout: Int) : this(layout, 0, null)

    constructor(layout: Int, vmVariableId: Int, stateViewModel: ViewModel?) {
        this.layout = layout
        this.vmVariableId = vmVariableId
        this.stateViewModel = stateViewModel
    }

    fun addBindingParam(
        variableId: Int,
        `object`: Any
    ): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, `object`)
        }
        return this
    }
}