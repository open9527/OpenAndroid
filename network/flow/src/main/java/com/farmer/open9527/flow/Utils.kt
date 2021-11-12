package com.farmer.open9527.flow

import android.util.Log


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
const val SHOW_TOAST = "show_toast"

fun toast(msg: String) {
    Log.e(SHOW_TOAST, msg)
}