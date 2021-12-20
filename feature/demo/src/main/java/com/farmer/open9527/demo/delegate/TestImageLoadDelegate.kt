package com.farmer.open9527.demo.delegate

import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.refresh.IRefreshView
import com.scwang.smart.refresh.layout.api.RefreshLayout


class TestImageLoadDelegate : CommonDelegate {

    private var mActivity: AppCompatActivity? = null

    constructor(activity: AppCompatActivity?) : super(activity) {
        this.mActivity = activity
    }

    override fun onBack() {
        mActivity?.finish()
    }
}