package com.farmer.open9527.meeting.main.mine

import android.os.Bundle
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonFragment
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager

class MineFragment : CommonFragment() {

    private lateinit var mViewModel: MineViewModel

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    override fun initViewModel() {
        mViewModel = getFragmentScopeViewModel(MineViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.meeting__mine__fragment, BR.vm, mViewModel)
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
}