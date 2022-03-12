package com.farmer.open9527.meeting.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.main.contacts.ContactsFragment
import com.farmer.open9527.meeting.main.home.HomeFragment
import com.farmer.open9527.meeting.main.login.LoginActivity
import com.farmer.open9527.meeting.main.mine.MineFragment
import com.farmer.open9527.meeting.main.msg.MsgFragment
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager
import com.farmer.open9527.recycleview.scroll.BottomSmoothScroller
import com.farmer.open9527.rmt.export.widget.ViewPager2FragmentStateAdapter
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest
import java.util.*


class MainActivity : CommonActivity() {

    private lateinit var mViewModel: MainViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(MainViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__main__activity, BR.vm, mViewModel)
    }

    override fun initStatusBar() {
        BarUtils.transparentStatusBar(this)
        BarUtils.setStatusBarLightMode(this, false)
    }

    override fun initView(bundle: Bundle?) {
        initNavigation()
        initViewPager()
    }

    override fun initRequest() {
        mViewModel.run {
            requestNavigation(iNavigationCell)

        }
    }

    private fun initNavigation() {
        mViewModel.valueLayoutManager.set(
            WrapContentGridLayoutManager(
                this,
                4,
                RecyclerView.VERTICAL,
                false
            )
        )
        mViewModel.valueNavigationAdapter.set(BaseBindingCellListAdapter())
    }

    private fun initViewPager() {
        val fragmentList = object : ArrayList<Fragment>() {
            init {
                add(MsgFragment.newInstance())
                add(ContactsFragment.newInstance())
                add(HomeFragment.newInstance())
                add(MineFragment.newInstance())
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

    companion object {
        fun startMainActivity() {
            ActivityUtils.startActivity(MainActivity::class.java)
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