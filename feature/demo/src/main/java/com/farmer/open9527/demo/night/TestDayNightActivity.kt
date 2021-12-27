package com.farmer.open9527.demo.night

import android.os.Bundle
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.demo.BR
import com.farmer.open9527.demo.R
import com.farmer.open9527.demo.activity_result.TestResultApiActivity
import com.farmer.open9527.demo.starter.ActionUtils
import com.farmer.open9527.demo.starter.BaseStarter
import java.util.ArrayList

class TestDayNightActivity : CommonActivity() {

    private var mViewModel: TestDayNightViewModel? = null
    private var mDayNightDelegate: TestDayNightDelegate? = null

    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(TestDayNightViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.test__day_night__activity, BR.vm, mViewModel!!)
    }

    override fun initTheme() {
        mDayNightDelegate = TestDayNightDelegate(mActivity)
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        mDayNightDelegate?.getColor(R.attr.testTextColor)?.let { mViewModel?.valueTitleColor?.set(it) }
    }

    override fun initEvent() {

        mViewModel?.getBackClickEvent()?.observe(this, {
            mDayNightDelegate?.onBack()
        })

        mViewModel?.getChangeThemeEvent()?.observe(this, {
            mDayNightDelegate?.onClickChangeTheme()
        })

        mViewModel?.getChangeGrayModelClickEvent()?.observe(this, {
            mDayNightDelegate?.changeGrayModel()
        })
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
            private const val SERVICE = "night_page"
            private const val PAGE_NAME = "跳转到\"TestDayNightActivity\"页"
        }
    }
}