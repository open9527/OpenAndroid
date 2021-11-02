package com.farmer.open9527.module.test

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter


/**
 *@author   open_9527
 *Create at 2021/11/2
 **/
object ViewAdapter {

    @BindingAdapter(value = ["bindImageViewUri"], requireAll = false)
    @JvmStatic
    fun setBindingImageView(imageView: ImageView?, uri: Uri?) {
        if (imageView == null) return
        imageView.setImageURI(uri)
    }
}