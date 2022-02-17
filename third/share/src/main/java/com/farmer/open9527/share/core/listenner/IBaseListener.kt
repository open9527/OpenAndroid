package com.farmer.open9527.share.core.listenner

import androidx.annotation.CallSuper

interface IBaseListener {
    @CallSuper
    fun onError(errorMsg: String?) {
        onComplete()
    }

    @CallSuper
    fun onCancel() {
        onComplete()
    }

    fun onComplete() {}
}