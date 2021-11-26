package com.farmer.open9527.common.binding.imageview

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
object ImageViewBindingAdapter {

    @BindingAdapter(value = ["bindImageViewUri"], requireAll = false)
    @JvmStatic
    fun setBindingImageView(imageView: ImageView?, uri: Uri?) {
        if (imageView == null || uri == null) return
        imageView.setImageURI(uri)
    }


    @BindingAdapter(value = ["bindImageViewResource"], requireAll = false)
    @JvmStatic
    fun setBindingImageView(imageView: ImageView?, @DrawableRes resId: Int?) {
        if (imageView == null || resId == null) return
        imageView.setImageResource(resId)
    }
}