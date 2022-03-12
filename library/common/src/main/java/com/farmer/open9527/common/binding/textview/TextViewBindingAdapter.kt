package com.farmer.open9527.common.binding.textview

import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
object TextViewBindingAdapter {


    @BindingAdapter(
        value = ["bindTextViewColor", "bindTextViewSelectColor", "bindTextViewSelect"],
        requireAll = false
    )
    @JvmStatic
    fun setBindingTextView(
        textView: TextView?,
        @ColorInt color: Int,
        @ColorInt selectColor: Int,
        select: Boolean
    ) {
        if (textView == null) return
        textView.setTextColor(if (select) selectColor else color)
    }
}