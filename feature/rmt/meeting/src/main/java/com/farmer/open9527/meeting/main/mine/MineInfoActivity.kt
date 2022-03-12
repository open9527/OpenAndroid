package com.farmer.open9527.meeting.main.mine

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.common.base.CommonFragment
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.contacts.ContactsActivity
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager

class MineInfoActivity : CommonActivity() {

    private lateinit var mViewModel: MineInfoViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(MineInfoViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__mine_info__activity, BR.vm, mViewModel)
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
        fun startMineInfoActivity() {
            ActivityUtils.startActivity(MineInfoActivity::class.java)
        }
    }
}