package com.farmer.open9527.rmt.pkg.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.AppUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.common.utils.ActivityManager
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager
import com.farmer.open9527.recycleview.scroll.BottomSmoothScroller
import com.farmer.open9527.rmt.export.widget.ViewPager2FragmentStateAdapter
import com.farmer.open9527.rmt.pkg.BR
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.main.home.HomeFragment
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest
import java.util.*


class MainActivity : CommonActivity() {

    private lateinit var mViewModel: MainViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(MainViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.rmt__main__activity, BR.vm, mViewModel)
    }

    override fun initView(bundle: Bundle?) {
        initNavigation()
        initViewPager()
    }

    override fun initRequest() {
        mViewModel.run {
            requestNavigation(iNavigationCell)
//            requestUpgrade(getRequest())
        }
    }

    private fun initNavigation() {
        mViewModel.valueLayoutManager.set(
            WrapContentGridLayoutManager(
                this,
                5,
                RecyclerView.VERTICAL,
                false
            )
        )
        mViewModel.valueNavigationAdapter.set(BaseBindingCellListAdapter())
    }

    private fun initViewPager() {
        val fragmentList = object : ArrayList<Fragment>() {
            init {
                add(HomeFragment.newInstance("首页"))
                add(HomeFragment.newInstance("视频"))
                add(HomeFragment.newInstance("动态"))
                add(HomeFragment.newInstance("音频"))
                add(HomeFragment.newInstance("新闻"))
            }
        }
        mViewModel.valueViewPager2Adapter.set(ViewPager2FragmentStateAdapter(this, fragmentList))
        mViewModel.valueViewPager2OffscreenPageLimit.set(fragmentList.size)
        mViewModel.valueViewPager2PageChangeCallback.set(iPageChangeCallback)
        mViewModel.valueViewPager2CurrentItem.set(0)
    }

    private fun onSwitchNavigation(position: Int) {
        val bottomSmoothScroller = BottomSmoothScroller(mActivity)
        bottomSmoothScroller.targetPosition = position
        mViewModel.valueLayoutManager.get()?.startSmoothScroll(bottomSmoothScroller)
        val cell = mViewModel.valueListData[position] as NavigationCell
        cell.updateStatus(position)
    }

    private var iPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            onSwitchNavigation(position)
        }
    }

    private var iNavigationCell = object : NavigationCell.INavigationCell {
        override fun onSwitchFragment(position: Int) {
            mViewModel.valueViewPager2CurrentItem.set(position)
        }
    }

    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }

    private var exitTime: Long = 0

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            toastLong(getString(R.string.exit_app))
            exitTime = System.currentTimeMillis()
        } else {
            AppUtils.exitApp()
        }
    }
}