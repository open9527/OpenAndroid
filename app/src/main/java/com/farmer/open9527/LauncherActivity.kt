package com.farmer.open9527

import android.os.Bundle
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.module.test.ui.ResultApiActivity


/**
 *@author   open_9527
 *Create at 2021/11/9
 **/
class LauncherActivity : CommonActivity() {

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        startActivity( ResultApiActivity::class.java)
        finish()
    }

    override fun onBackPressed() {}
}