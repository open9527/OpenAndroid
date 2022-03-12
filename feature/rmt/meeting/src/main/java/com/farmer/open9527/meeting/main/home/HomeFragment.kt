package com.farmer.open9527.meeting.main.home

import android.os.Bundle
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonFragment
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest

class HomeFragment : CommonFragment() {

    private lateinit var mViewModel: HomeViewModel

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }

    }

    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__home__fragment, BR.vm, mViewModel)
    }

    override fun initView(bundle: Bundle?) {
    }

    override fun initEvent() {

    }

    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }
}