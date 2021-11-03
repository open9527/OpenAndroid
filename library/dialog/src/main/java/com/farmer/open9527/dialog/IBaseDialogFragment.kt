package com.farmer.open9527.dialog

import android.view.View
import android.view.Window
import androidx.annotation.LayoutRes


/**
 *@author   open_9527
 *Create at 2021/11/3
 **/
interface IBaseDialogFragment {

    fun bindTheme(): Int {
        return 0
    }

    @LayoutRes
    fun bindLayout(): Int {
        return 0
    }


    fun initView(dialog: BaseDialogFragment, contentView: View) {}

    fun getDataBindingConfig(): DialogDataBindingConfig? {
        return DialogDataBindingConfig()
    }

    fun setWindowStyle(window: Window) {}

}