package com.farmer.open9527.rmt.ui.main

import android.os.Bundle
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.rmt.BR
import com.farmer.open9527.rmt.R


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
class MainActivity : CommonActivity() {

    private var mViewModel: MainViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(MainViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.main__activity, BR.vm, mViewModel)
    }

    override fun initView(bundle: Bundle?) {

    }

    override fun initRequest() {
        mViewModel?.requestHomePanelList()
    }

    override fun initEvent() {
        mViewModel?.homePanelListLiveData?.observe(this, {
            LogUtils.i(TAG, "homePanelListLiveData:" + GsonUtils.toJson(it))

        })
    }
}
