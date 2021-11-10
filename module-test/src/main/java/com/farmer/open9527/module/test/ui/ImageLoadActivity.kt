package com.farmer.open9527.module.test.ui

import android.os.Bundle
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.viewmodel.ImageLoadViewModel
import com.farmer.open9527.module.test.vo.image.RawType
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.decoration.GridSpaceItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager


/**
 *@author   open_9527
 *Create at 2021/11/9
 **/
class ImageLoadActivity : CommonActivity() {

    private var mViewModel: ImageLoadViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(ImageLoadViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.image_load__activity, BR.vm, mViewModel!!)
    }


    override fun initView(bundle: Bundle?) {
        initRecycleView()
    }

    override fun initRequest() {
        mViewModel?.loadImages(RawType.GIF.resId)
        mViewModel?.loadImages(RawType.SVG.resId)
        mViewModel?.loadImages(RawType.JPG.resId)
    }


    private fun initRecycleView() {
        mViewModel?.valueLayoutManager?.set(WrapContentGridLayoutManager(this, 2))
        mViewModel?.valueItemDecoration?.set(
            GridSpaceItemDecoration(SizeUtils.dp2px(10f), true)
        )
        mViewModel?.valueAdapter?.set(BaseBindingCellListAdapter())
    }
}