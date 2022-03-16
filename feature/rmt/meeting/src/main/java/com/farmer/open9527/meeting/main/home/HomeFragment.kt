package com.farmer.open9527.meeting.main.home

import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonFragment
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.webview.bridge.X5WebView

class HomeFragment : CommonFragment() {

    private lateinit var mViewModel: HomeViewModel
    private var x5WebView: X5WebView? = null


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
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun initRequest() {
        x5WebView = mActivity?.findViewById<X5WebView>(R.id.x5_web)
                x5WebView?.loadUrl(
        "http://61.129.134.49/zyhy/#/init?token=" + SPUtils.getInstance().getString("token")
        )
    }

    open inner class ClickProxy {
        var onBackClick = View.OnClickListener {
//            mActivity?.finish()
            if (x5WebView?.canGoBack() == true) {
                x5WebView?.goBack()//返回上个页面
            }
        }
    }
}