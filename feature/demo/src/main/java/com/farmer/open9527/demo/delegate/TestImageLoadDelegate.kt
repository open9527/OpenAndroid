package com.farmer.open9527.demo.delegate

import androidx.appcompat.app.AppCompatActivity


class TestImageLoadDelegate : CommonDelegate {

    private var mActivity: AppCompatActivity? = null

    constructor(activity: AppCompatActivity?) : super(activity) {
        this.mActivity = activity
    }

    override fun onBack() {
        mActivity?.finish()
    }
}