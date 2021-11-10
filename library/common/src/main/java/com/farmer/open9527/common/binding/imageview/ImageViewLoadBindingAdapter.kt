package com.farmer.open9527.common.binding.imageview

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
object ImageViewLoadBindingAdapter {

    @BindingAdapter(
        value = [
            "bindImageViewLoadUrl",
            "bindImageViewLoadWidth",
            "bindImageViewLoadHeight",
            "bindImageViewLoadPlaceholderRes",
            "bindImageViewLoadPlaceholderDrawable",
            "bindImageViewLoadCircle",
            "bindImageViewLoadRound",
            "bindImageViewLoadRoundRadius",
            "bindImageViewLoadTransformation",
        ], requireAll = false
    )
    @JvmStatic
    fun setBindingImageViewLoad(
        imageView: ImageView?,
        url: String?,
        width: Int?,
        height: Int?,
        @DrawableRes placeholderRes: Int?,
        placeholderDrawable: Drawable?,
        hasCircle: Boolean,
        hasRound: Boolean,
        roundRadius: Float,
        transformation: Transformation?,
    ) {
        imageView?.load(url) {
            if (width != null && height != null) {
                size(width, height)
            }
            if (placeholderRes != null) {
                placeholder(placeholderRes)
                fallback(placeholderRes)
                error(placeholderRes)
            }
            if (placeholderDrawable != null) {
                placeholder(placeholderDrawable)
                fallback(placeholderDrawable)
                error(placeholderDrawable)

            }
            if (hasCircle) {
                transformations(CircleCropTransformation())
            }
            if (hasRound) {
                transformations(RoundedCornersTransformation(roundRadius))
            }
            if (transformation != null) {
                transformations(transformation)
            }
        }
    }
}