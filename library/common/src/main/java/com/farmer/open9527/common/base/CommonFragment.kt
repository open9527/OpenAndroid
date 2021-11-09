package com.farmer.open9527.common.base

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.BaseFragment


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
abstract class CommonFragment : BaseFragment() {

    private val keyHidden = "STATE_SAVE_IS_HIDDEN"

    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(TAG, "onCreate")
        val fm = parentFragmentManager ?: return
        if (savedInstanceState != null) {
            val isSupportHidden =
                savedInstanceState.getBoolean(keyHidden)
            val ft = fm.beginTransaction()
            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }
            ft.commitAllowingStateLoss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
        initView(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            initRequest()
            isFirstLoad = false
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(keyHidden, isHidden)
    }


    open fun initEvent() {

    }

    open fun initView(bundle: Bundle?) {

    }

    open fun initRequest() {

    }
}