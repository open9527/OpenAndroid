package com.farmer.open9527.recycleview.animator

import android.animation.Animator
import android.view.View


interface BaseAnimation {
    fun animators(view: View): Array<Animator>
}