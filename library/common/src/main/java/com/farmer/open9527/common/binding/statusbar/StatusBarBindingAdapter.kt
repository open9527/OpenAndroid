package com.farmer.open9527.common.binding.statusbar

import android.view.View
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.BarUtils


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
object StatusBarBindingAdapter {
    @BindingAdapter(value = ["bindStatusBar"], requireAll = false)
    @JvmStatic
    fun setBindingStatusBar(view: View, height: Int) {
        BarUtils.setStatusBarCustom(view)
    }
}