package com.farmer.open9527.meeting.main.msg

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
import com.hjq.http.request.PostRequest

class MsgFragment : CommonFragment() {

    private lateinit var mViewModel: MsgViewModel

    companion object {
        fun newInstance(): MsgFragment {
            val bundle = Bundle()
            val fragment = MsgFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(MsgViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__msg__fragment, BR.vm, mViewModel)
    }

    override fun initRequest() {
        mViewModel.requestMsgData()
    }

    override fun initView(bundle: Bundle?) {
        initRecycleView()
    }


    private fun initRecycleView() {
        mViewModel.valueLayoutManager.set(WrapContentLinearLayoutManager(mActivity))
        mViewModel.valueAdapter.set(BaseBindingCellListAdapter())
    }

    open inner class ClickProxy {
        var onBackClick = View.OnClickListener {
            LogUtils.i(TAG, "onBackClick")
        }
    }


    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }
}