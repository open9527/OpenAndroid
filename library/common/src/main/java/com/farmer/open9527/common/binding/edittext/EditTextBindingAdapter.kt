package com.farmer.open9527.common.binding.edittext

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
object EditTextBindingAdapter {


    @BindingAdapter(
        value = ["bindEditTextShowPassword"],
        requireAll = false
    )
    @JvmStatic
    fun setBindingEditText(
        editText: EditText?,
        show: Boolean
    ) {
        if (editText == null) return
        if (show) {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        editText.setSelection(editText.text.length)

    }
}