package com.farmer.open9527.demo.load_image

import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.demo.R
import com.farmer.open9527.demo.BR
import com.farmer.open9527.demo.api.vo.image.RawType
import com.farmer.open9527.demo.proxy.click.CommonClickProxy
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.decoration.GridSpaceItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager
import com.farmer.open9527.refresh.IRefreshView

import com.scwang.smart.refresh.layout.api.RefreshLayout


/**
 *@author   open_9527
 *Create at 2021/11/9
 **/
class TestImageLoadActivity : CommonActivity() {

    private var mViewModel: TestImageLoadViewModel? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(TestImageLoadViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.test__image_load__activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun initView(bundle: Bundle?) {
        initRecycleView()
        mViewModel?.valueIImageLoadCell?.set(iRegisterCallBack)
    }

    override fun initRequest() {
        requestImages()

    }

    private fun requestImages() {
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

    inner class ClickProxy : CommonClickProxy() {
        override fun onBack() {
            finish()
        }

        val iRefreshView = object : IRefreshView {
            override fun onRefresh(refreshLayout: RefreshLayout?, hasRefresh: Boolean) {
                if (hasRefresh) {
                    mViewModel?.loadImages(RawType.GIF.resId)
                } else {
                    mViewModel?.loadImages(RawType.SVG.resId)
                }
                mViewModel?.valueNoMoreData?.set(hasRefresh)
            }
        }
    }

    private val iRegisterCallBack = object : TestImageLoadCell.IImageLoadCell {
        override fun registerCallBack(url: String?) {
            val intent = Intent()
            intent.putExtra("url", url)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}