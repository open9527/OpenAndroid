package com.farmer.open9527.launcher

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.demo.leetcode.Leetcode
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

    override fun getContext(): Context {
        TODO("Not yet implemented")
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()
        mViewModel?.valueILauncherCell?.set(iLauncherCell)

//        LogUtils.i(TAG, "Leetcode: " + Leetcode.sum(intArrayOf(2, 7, 11, 15), 9).iterator().toString())
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
        override fun launcherActivity(cls: Class<out Activity>) {
            startActivity(cls)
        }
    }


    private var exitTime: Long = 0

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            toast(getString(R.string.launch_exit_app))
            exitTime = System.currentTimeMillis()
        } else {
            AppUtils.exitApp()
        }
    }
}