package com.farmer.open9527.demo.load_image

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.demo.BR
import com.farmer.open9527.demo.R
import com.farmer.open9527.demo.activity_result.TestResultApiActivity
import com.farmer.open9527.demo.api.vo.image.RawType
import com.farmer.open9527.demo.delegate.TestImageLoadDelegate
import com.farmer.open9527.demo.starter.ActionUtils
import com.farmer.open9527.demo.starter.BaseStarter
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.animator.ScaleInAnimation
import com.farmer.open9527.recycleview.animator.SlideInUpAnimator
import com.farmer.open9527.recycleview.decoration.GridSpaceItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager
import com.farmer.open9527.refresh.IRefreshView
import com.scwang.smart.refresh.layout.api.RefreshLayout
import java.util.ArrayList


/**
 *@author   open_9527
 *Create at 2021/11/9
 **/
class TestImageLoadActivity : CommonActivity(), IRefreshView {

    private var mViewModel: TestImageLoadViewModel? = null
    private var mDelegate: TestImageLoadDelegate? = null
    private var mPage: Int = 1

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(TestImageLoadViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.test__image_load__activity, BR.vm, mViewModel!!)
    }


    override fun initView(bundle: Bundle?) {
        initRecycleView()
        mViewModel?.valueIImageLoadCell?.set(iRegisterCallBack)
        mDelegate = TestImageLoadDelegate(this)
        mViewModel?.valueRefreshEvent?.set(this)
    }

    override fun initRequest() {
        requestImages()
    }

    override fun initEvent() {
        mViewModel?.getBackClickEvent()?.observe(this, {
            mDelegate?.onBack()
        })
    }

    private fun requestImages() {
        mViewModel?.loadImages(RawType.JPG.resId, mPage)
//        mViewModel?.loadImages(RawType.GIF.resId)
//        mViewModel?.loadImages(RawType.SVG.resId)
    }


    private fun initRecycleView() {
        mViewModel?.valueLayoutManager?.set(WrapContentGridLayoutManager(this, 2))
        mViewModel?.valueItemAnimator?.set(SlideInUpAnimator(OvershootInterpolator(1f)))
        mViewModel?.valueAnimation?.set(ScaleInAnimation())
        mViewModel?.valueItemDecoration?.set(
            GridSpaceItemDecoration(SizeUtils.dp2px(10f), true)
        )
        mViewModel?.valueAdapter?.set(BaseBindingCellListAdapter())
    }

    override fun onRefresh(refreshLayout: RefreshLayout?, hasRefresh: Boolean) {
        if (hasRefresh) {
            mPage = 1
            mViewModel?.loadImages(RawType.JPG.resId, mPage)
        } else {
            mPage++
            mViewModel?.loadImages(RawType.SVG.resId, mPage)
        }
        mViewModel?.valueNoMoreData?.set(hasRefresh)
    }


    private val iRegisterCallBack = object : TestImageLoadCell.IImageLoadCell {
        override fun registerCallBack(url: String?) {
            val intent = Intent()
            intent.putExtra("url", url)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    class Starter : BaseStarter() {
        override fun name(serviceName: String): String {
            return PAGE_NAME
        }

        override fun usage(serviceName: String): String {
            return APP_SCHEMA + SERVICE
        }

        override fun supportService(): List<String> {
            val serviceList: MutableList<String> = ArrayList()
            serviceList.add(SERVICE)
            return serviceList
        }

        override fun startActivity(serviceName: String, queryParams: Map<String, String>?) {
            ActionUtils.startActivity(TestResultApiActivity::class.java)
        }

        companion object {
            private const val SERVICE = "image_load_page"
            private const val PAGE_NAME = "跳转到\"TestImageLoadActivity\"页"
        }
    }
}