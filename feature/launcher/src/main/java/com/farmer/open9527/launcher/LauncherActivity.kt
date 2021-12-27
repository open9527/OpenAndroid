package com.farmer.open9527.launcher

import android.os.Bundle
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.decoration.GridSpaceItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager


/**
 *@author   open_9527
 *Create at 2021/11/12
 **/
class LauncherActivity : CommonActivity() {

    private var mViewModel: LauncherViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(LauncherViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.launcher__activity, BR.vm, mViewModel)
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()
        mViewModel?.valueILauncherCell?.set(iLauncherCell)

    }




    override fun initRequest() {
        mViewModel?.loadLauncherCells()
    }


    private fun initRecycleView() {
        mViewModel?.valueLayoutManager?.set(WrapContentGridLayoutManager(this, 1))
        mViewModel?.valueItemDecoration?.set(
            GridSpaceItemDecoration(10, true)
        )
        mViewModel?.valueAdapter?.set(BaseBindingCellListAdapter())
    }

    private var iLauncherCell = object : LauncherCell.ILauncherCell {
        override fun launcherActivity(cls: Class<*>) {
            startActivity(cls)
        }
    }

    private var exitTime: Long = 0

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showLong(getString(R.string.launch_exit_app))
            exitTime = System.currentTimeMillis()
        } else {
            AppUtils.exitApp()
        }
    }
}