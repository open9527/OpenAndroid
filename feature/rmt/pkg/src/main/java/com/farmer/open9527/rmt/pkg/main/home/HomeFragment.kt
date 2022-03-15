package com.farmer.open9527.rmt.pkg.main.home

import android.os.Bundle
import com.blankj.utilcode.util.AppUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonFragment
import com.farmer.open9527.rmt.pkg.BR
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.webview.bridge.X5WebView
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest

class HomeFragment : CommonFragment() {

    private lateinit var mViewModel: HomeViewModel

    companion object {
        fun newInstance(title: String): HomeFragment {
            val bundle = Bundle()
            bundle.putString("title", title)
            val fragment = HomeFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(HomeViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.rmt__home__fragment, BR.vm, mViewModel)
    }

    override fun initView(bundle: Bundle?) {
        mViewModel.valueTitle.set(getString("title"))
        val x5WebView = mActivity?.findViewById<X5WebView>(R.id.x5_web)
        x5WebView?.buildUserAgent("Rmt/ChongMing; Version/" + AppUtils.getAppVersionName())
        x5WebView?.loadUrl("https://k8s.shmedia.tech/api/app/doc/release/rm_js_sdk_testcase.html")

//        val mWebAgreement = WebAgreementImpl.newInstance(x5WebView)
//
//        //版本号
//        mWebAgreement?.getVersion { webBaseBean ->
//            val webVersionRp = WebVersionRp()
//            val appBean = WebVersionRp.AppBean()
//            val jsSdkBean = WebVersionRp.JsSdkBean()
//            appBean.name = VersionVo.APP_NAME
//            appBean.version = AppUtils.getAppVersionName()
//            jsSdkBean.name = "rmt-js-sdk"
//            jsSdkBean.version = "2.0.0"
//            webVersionRp.app = appBean
//            webVersionRp.jssdk = jsSdkBean
//            webBaseBean.callback.onCallback(CallbackParam.success(webVersionRp).toJSON())
//            webBaseBean.callback.onCallback(CallbackParam.complete().toJSON())
//        }

    }

    override fun initRequest() {
        mViewModel.requestHomePanelApi(getRequest())
    }

    override fun initEvent() {

    }

    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }
}