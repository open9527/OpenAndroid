package com.farmer.open9527.module.test.vo.image

import androidx.annotation.RawRes
import com.farmer.open9527.module.test.R

/**
 *@author   open_9527
 *Create at 2021/11/9
 **/
enum class RawType(@RawRes val resId: Int) {
    JPG(R.raw.jpgs),
    GIF(R.raw.gifs),
    SVG(R.raw.svgs),
}