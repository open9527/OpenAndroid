package com.farmer.open9527.share.core.listenner

import androidx.annotation.CallSuper

class ShareListener : IBaseListener {
    @CallSuper
    fun onSuccess() {
        onComplete()
    }

    override fun onComplete() {}
}