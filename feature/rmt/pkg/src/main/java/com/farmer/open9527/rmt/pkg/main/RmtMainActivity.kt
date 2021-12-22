package com.farmer.open9527.rmt.pkg.main

import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.BR

class RmtMainActivity : CommonActivity() {

    private var mViewModel: RmtMainViewModel? = null


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(RmtMainViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.rmt__main__activity, BR.vm, mViewModel!!)
    }
}