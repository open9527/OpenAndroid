package com.farmer.open9527.meeting.main.login

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.MainActivity
import com.farmer.open9527.meeting.main.login.PasswordLoginCell.IPasswordLogin
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest

class LoginActivity : CommonActivity() {

    private lateinit var mViewModel: LoginViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(LoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__login__activity, BR.vm, mViewModel)
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun initRequest() {
        mViewModel.requestPasswordData()
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()

        mViewModel.valueIPasswordLogin.set(iPasswordLogin)
        mViewModel.valueICodeLogin.set(iCodeLogin)
    }

    override fun initEvent() {
        mViewModel.loginEvent.observe(this) {
            MainActivity.startMainActivity()
        }
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


    private val iPasswordLogin = object : IPasswordLogin {
        override fun login(mobile: String?, password: String?) {
            mViewModel.requestLoginPassword(getRequest(), mobile, password)
        }

        override fun switch() {
            mViewModel.requestCodeData()

        }
    }

    private val iCodeLogin = object : CodeLoginCell.ICodeLogin {
        override fun login(mobile: String?, code: String?) {
            mViewModel.requestLoginCode(mobile, code)
        }

        override fun switch() {
            mViewModel.requestPasswordData()
        }
    }

    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }

    companion object {
        fun startLoginActivity() {
            ActivityUtils.startActivity(LoginActivity::class.java)
        }
    }
}