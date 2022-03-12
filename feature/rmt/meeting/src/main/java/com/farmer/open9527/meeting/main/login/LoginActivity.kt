package com.farmer.open9527.meeting.main.login

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.MainActivity
import com.farmer.open9527.meeting.main.login.PasswordLoginCell.IPasswordLogin
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager

class LoginActivity : CommonActivity() {

    private lateinit var mViewModel: LoginViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(LoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__login__activity, BR.vm, mViewModel)
    }


    override fun initRequest() {
        mViewModel.requestPasswordData()
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()
        mViewModel.valueIPasswordLogin.set(iPasswordLogin)
        mViewModel.valueICodeLogin.set(iCodeLogin)
    }

    private fun initRecycleView() {
        mViewModel.valueLayoutManager.set(WrapContentLinearLayoutManager(mActivity))
        mViewModel.valueAdapter.set(BaseBindingCellListAdapter())
    }


    private val iPasswordLogin = object : IPasswordLogin {
        override fun login(mobile: String?, password: String?) {
            MainActivity.startMainActivity()
        }

        override fun switch() {
            mViewModel.requestCodeData()

        }
    }

    private val iCodeLogin = object : CodeLoginCell.ICodeLogin {
        override fun login(mobile: String?, code: String?) {
            MainActivity.startMainActivity()
        }

        override fun switch() {
            mViewModel.requestPasswordData()
        }
    }

    companion object {
        fun startLoginActivity() {
            ActivityUtils.startActivity(LoginActivity::class.java)
        }
    }
}