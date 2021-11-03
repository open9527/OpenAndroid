package com.farmer.open9527.dialog

import android.view.Gravity


/**
 *@author   open_9527
 *Create at 2021/11/3
 **/
object DialogFragmentGravity {

    var LEFT_TOP = Gravity.START or Gravity.TOP
    var CENTER_TOP = Gravity.CENTER_HORIZONTAL or Gravity.TOP
    var RIGHT_TOP = Gravity.END or Gravity.TOP

    var LEFT_CENTER = Gravity.START or Gravity.CENTER_VERTICAL
    var CENTER_CENTER = Gravity.CENTER

    var RIGHT_CENTER = Gravity.END or Gravity.CENTER_VERTICAL
    var LEFT_BOTTOM = Gravity.START or Gravity.BOTTOM
    var CENTER_BOTTOM = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
    var RIGHT_BOTTOM = Gravity.END or Gravity.BOTTOM

}