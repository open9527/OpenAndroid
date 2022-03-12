package com.farmer.open9527.common.binding.imageview

import android.graphics.drawable.Drawable
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


    @BindingAdapter(
        value = ["bindImageViewResource", "bindImageViewSelectResource", "bindImageViewSelect"],
        requireAll = false
    )
    @JvmStatic
    fun setBindingImageView(
        imageView: ImageView?,
        @DrawableRes resId: Int,
        @DrawableRes selectResId: Int,
        select: Boolean
    ) {
        if (imageView == null) return
        imageView.setImageResource(if (select) selectResId else resId)
    }

    @BindingAdapter(
        value = ["bindImageViewDrawable", "bindImageViewSelectDrawable", "bindImageViewSelect"],
        requireAll = false
    )
    @JvmStatic
    fun setBindingImageView(
        imageView: ImageView?,
        drawable: Drawable?,
        selectDrawable: Drawable?,
        select: Boolean
    ) {
        if (imageView == null || drawable == null) return
        imageView.setImageDrawable(if (select) selectDrawable else drawable)
    }
}