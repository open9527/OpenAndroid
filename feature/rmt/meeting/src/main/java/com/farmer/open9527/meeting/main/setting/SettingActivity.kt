package com.farmer.open9527.meeting.main.setting

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager

class SettingActivity : CommonActivity() {

    private lateinit var mViewModel: SettingViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(SettingViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__setting__activity, BR.vm, mViewModel)
    }

    override fun initStatusBar() {
        BarUtils.transparentStatusBar(this)
        BarUtils.setStatusBarLightMode(this, false)
    }

    override fun initRequest() {
        mViewModel.requestMsgData()
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()
    }

    private fun initRecycleView() {
        mViewModel.valueLayoutManager.set(WrapContentLinearLayoutManager(mActivity))
        mViewModel.valueAdapter.set(BaseBindingCellListAdapter())
    }


    companion object {
        fun startSettingActivity() {
            ActivityUtils.startActivity(SettingActivity::class.java)
        }
    }
}