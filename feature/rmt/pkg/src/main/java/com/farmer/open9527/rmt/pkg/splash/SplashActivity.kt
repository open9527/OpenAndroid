package com.farmer.open9527.rmt.pkg.splash

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.CollectionUtils
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.rmt.export.http.vo.resource.ResourceVo
import com.farmer.open9527.rmt.export.http.vo.sys.BillboardVo
import com.farmer.open9527.rmt.pkg.BR
import com.farmer.open9527.rmt.pkg.R
import com.farmer.open9527.rmt.pkg.databinding.RmtSplashViewBinding
import com.farmer.open9527.rmt.pkg.main.MainActivity
import com.hjq.http.EasyHttp
import com.hjq.http.request.PostRequest
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class SplashActivity : CommonActivity() {

    private lateinit var mViewModel: SplashViewModel

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(SplashViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.rmt__splash__activity, BR.vm, mViewModel)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun initTheme() {
        window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }

    override fun initRequest() {
        mViewModel.requestBillboard(getRequest())
    }

    override fun initView(bundle: Bundle?) {
        checkTaskRoot()
        super.initView(bundle)
        startMainActivity()
    }

    override fun initEvent() {
        mViewModel.billboardEvent.observe(this) { data ->
            if (!CollectionUtils.isEmpty(data)) {
                mViewModel.valueBannerAdapter.set(BannerAdapter())
                mViewModel.valueBannerData.set(data)
                mViewModel.valueBannerVisibility.set(View.VISIBLE)
                skipSplash(data[0].skipTime)
            } else {
                startMainActivity()
            }
        }
    }


    private fun checkTaskRoot() {
        // 问题及方案：https://www.cnblogs.com/net168/p/5722752.html
        // 如果当前 Activity 不是任务栈中的第一个 Activity
        if (!isTaskRoot) {
            val intent: Intent? = intent
            // 如果当前 Activity 是通过桌面图标启动进入的
            if (((intent != null) && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
                        && (Intent.ACTION_MAIN == intent.action))
            ) {
                // 对当前 Activity 执行销毁操作，避免重复实例化入口
                finish()
                return
            }
        }
    }

    open inner class ClickProxy {
        var onSkipClick = View.OnClickListener {
            LogUtils.i(TAG, "onSkipClick")
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(MainActivity::class.java)
        finish()
    }

    private fun skipSplash(skipTime: Int) {
        postDelayed({
            startMainActivity()
        }, skipTime * 1000L)
    }


    inner class BannerAdapter : BaseBannerAdapter<BillboardVo>() {
        override fun bindData(
            holder: BaseViewHolder<BillboardVo>,
            billboardVo: BillboardVo,
            position: Int,
            pageSize: Int
        ) {
            val binding = DataBindingUtil.bind<ViewDataBinding>(holder.itemView)!!
            if (billboardVo != null) {
                val resourceVo: ResourceVo? = billboardVo.resource
                binding.setVariable(BR.imageUrl, resourceVo?.sourceUrl)
            }
        }

        override fun getLayoutId(viewType: Int): Int {
            return R.layout.rmt__splash_banner_item
        }
    }


    private fun addADContentView() {
        BarUtils.setNavBarVisibility(this, false)
        BarUtils.setStatusBarVisibility(this, false)
        val mLayoutInflater = LayoutInflater.from(this)
        val mView: View = mLayoutInflater.inflate(R.layout.rmt__splash__view, null)
        val mViewBinding = DataBindingUtil.bind<RmtSplashViewBinding>(mView)
        if (mViewBinding != null) {
            mViewBinding.lifecycleOwner = this
        }
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        addContentView(mView, layoutParams)
        postDelayed({
            (mView.parent as ViewGroup).removeView(mView)
            BarUtils.setNavBarVisibility(this, true)
            BarUtils.setStatusBarVisibility(this, true)
            BarUtils.transparentStatusBar(this)
            BarUtils.setStatusBarLightMode(this, true)
        }, 3000)
    }

    override fun onBackPressed() {
    }

    private fun getRequest(): PostRequest {
        return EasyHttp.post(this)
    }
}