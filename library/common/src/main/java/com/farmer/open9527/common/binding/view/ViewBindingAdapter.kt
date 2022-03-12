package com.farmer.open9527.common.binding.view

import android.icu.number.IntegerWidth
import android.view.View
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.BarUtils


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
object ViewBindingAdapter {
    @BindingAdapter(value = ["bindViewHeight"], requireAll = false)
    @JvmStatic
    fun setBindingViewHeight(view: View?, height: Int) {
        if (view == null) return
        val params = view.layoutParams
        if (params.height != height) {
            params.height = height
            view.layoutParams = params
        }

    }

    @BindingAdapter(value = ["bindViewWidth"], requireAll = false)
    @JvmStatic
    fun setBindingViewWidth(view: View?, width: Int) {
        if (view == null) return
        val params = view.layoutParams
        if (params.height != width) {
            params.width = width
            view.layoutParams = params
        }

    }
}