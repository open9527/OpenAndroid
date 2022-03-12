package com.farmer.open9527.common.action

import androidx.annotation.StringRes
import com.blankj.utilcode.util.ToastUtils

interface ToastAction {

    fun toast(text: CharSequence?) {
        ToastUtils.showShort(text)
    }


    fun toast(@StringRes id: Int) {
        ToastUtils.showShort(id)
    }

    fun toastLong(text: CharSequence?) {
        ToastUtils.showLong(text)
    }

    fun toastLong(@StringRes id: Int) {
        ToastUtils.showLong(id)
    }
}