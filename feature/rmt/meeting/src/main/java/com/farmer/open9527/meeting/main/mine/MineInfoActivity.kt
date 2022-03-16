package com.farmer.open9527.meeting.main.mine

import android.os.Bundle
import android.view.View
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
            .addBindingParam(BR.click, ClickProxy())
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
        mViewModel.valueName.set(getString("name"))
        mViewModel.valueDepartment.set(getString("department"))
        mViewModel.valueGender.set(getString("gender"))
        mViewModel.valueMobile.set(getString("mobile"))


    }

    private fun initRecycleView() {
        mViewModel.valueLayoutManager.set(WrapContentLinearLayoutManager(mActivity))
        mViewModel.valueAdapter.set(BaseBindingCellListAdapter())
    }
    open inner class ClickProxy {
        var onBackClick = View.OnClickListener {
            finish()
        }
    }

    companion object {

        fun startMineInfoActivity(name: String?, gender: String?, department: String?, mobile: String?) {
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("gender", gender)
            bundle.putString("department", department)
            bundle.putString("mobile", mobile)
            ActivityUtils.startActivity(bundle, MineInfoActivity::class.java)
        }
    }
}