package com.farmer.open9527.module.test.ui

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.viewmodel.NetWorkViewModel
import com.farmer.open9527.recycleview.decoration.SpacesItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager


/**
 *@author   open_9527
 *Create at 2021/10/18
 **/
class NetWorkActivity : CommonActivity() {

    private var mViewModel: NetWorkViewModel? = null


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(NetWorkViewModel::class.java);

    }

//    private val mViewModel by viewModels<NetWorkViewModel>()



    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.net_work__activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun initEvent() {
        mViewModel?.userLiveData?.observeState(this) {
            onSuccess {
                LogUtils.i(TAG, "userLiveData: onSuccess")
//                LogUtils.i(TAG, "userLiveData: " + it.toString())
                mViewModel?.fetchWxArticleFromNet()
            }
        }
        mViewModel?.wxArticleLiveData?.observeState(this) {
            onSuccess {
                LogUtils.i(TAG, "wxArticleLiveData: onSuccess")
//                LogUtils.i(TAG, "wxArticleLiveData: " + it.toString())
            }

        }
        mViewModel?.initRecycleView(
            WrapContentLinearLayoutManager(this),
            SpacesItemDecoration(this, RecyclerView.VERTICAL)
        )
    }

    inner class ClickProxy {
        var requestClick = View.OnClickListener {
            LogUtils.i(TAG, "requestClick")
            mViewModel?.fetchWxArticleFromNet();
        }

        var loginClick = View.OnClickListener {
            LogUtils.i(TAG, "loginClick")
//            mViewModel?.login("open_9527", "kuaige930817")
            val intent = Intent()
            intent.putExtra("data", "data from NetWorkActivity")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}