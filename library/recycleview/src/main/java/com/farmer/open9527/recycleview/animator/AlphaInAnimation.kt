package com.farmer.open9527.recycleview.animator

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator


class AlphaInAnimation @JvmOverloads constructor(private val mFrom: Float = DEFAULT_ALPHA_FROM) : BaseAnimation {
    override fun animators(view: View): Array<Animator> {
        val animator = ObjectAnimator.ofFloat(view, "alpha", mFrom, 1f)
        animator.duration = 300L
        animator.interpolator = LinearInterpolator()
        return arrayOf(animator)
    }

    companion object {
        private const val DEFAULT_ALPHA_FROM = 0f
    }

}