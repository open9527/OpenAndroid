package com.farmer.open9527.meeting.main.contacts

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest
import android.content.Intent


class ContactsActivity : CommonActivity() {

    private lateinit var mViewModel: ContactsActivityViewModel


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(ContactsActivityViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__contacts__activity, BR.vm, mViewModel)
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
        mViewModel.valueTitle.set(getString("title"))
        initRecycleView()
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
        fun startContactsActivity(title: String?) {
            val bundle = Bundle()
            bundle.putString("title", title)
            ActivityUtils.startActivity(bundle, ContactsActivity::class.java)
        }
    }


    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }
}