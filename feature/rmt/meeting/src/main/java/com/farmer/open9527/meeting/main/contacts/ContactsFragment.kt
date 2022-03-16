package com.farmer.open9527.meeting.main.contacts

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonFragment
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager
import com.hjq.http.EasyHttp
import com.hjq.http.request.GetRequest
import com.hjq.http.request.PostRequest

class ContactsFragment : CommonFragment() {

    private lateinit var mViewModel: ContactsViewModel

    companion object {
        fun newInstance(): ContactsFragment {
            val bundle = Bundle()
            val fragment = ContactsFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(ContactsViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__contacts__fragment, BR.vm, mViewModel)
    }

    override fun initRequest() {
//        mViewModel.requestMsgData()
        mViewModel.requestMailList(getRequest())
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()
    }


    private fun initRecycleView() {
        mViewModel.valueLayoutManager.set(WrapContentLinearLayoutManager(mActivity))
        mViewModel.valueAdapter.set(BaseBindingCellListAdapter())
    }


    private fun getRequest(): GetRequest {
        return EasyHttp.get(this)
    }
}