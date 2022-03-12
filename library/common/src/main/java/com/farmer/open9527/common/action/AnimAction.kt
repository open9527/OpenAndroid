package com.farmer.open9527.common.action

import com.farmer.open9527.common.R


interface AnimAction {

    companion object {

        /** 默认动画效果 */
        const val ANIM_DEFAULT: Int = -1

        /** 没有动画效果 */
        const val ANIM_EMPTY: Int = 0

        /** 缩放动画 */
        val ANIM_SCALE: Int = R.style.ScaleAnimStyle

        /** IOS 动画 */
        val ANIM_IOS: Int = R.style.IOSAnimStyle

        /** 吐司动画 */
        const val ANIM_TOAST: Int = android.R.style.Animation_Toast

        /** 顶部弹出动画 */
        val ANIM_TOP: Int = R.style.TopAnimStyle

        /** 底部弹出动画 */
        val ANIM_BOTTOM: Int = R.style.BottomAnimStyle

        /** 左边弹出动画 */
        val ANIM_LEFT: Int = R.style.LeftAnimStyle

        /** 右边弹出动画 */
        val ANIM_RIGHT: Int = R.style.RightAnimStyle
    }
}