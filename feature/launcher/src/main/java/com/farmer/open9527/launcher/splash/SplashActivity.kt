package com.farmer.open9527.launcher.splash

import android.os.Bundle
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.launcher.LauncherActivity


/**
 *@author   open_9527
 *Create at 2021/11/26
 **/
class SplashActivity : CommonActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(LauncherActivity::class.java)
    }

    override fun onBackPressed() {

    }
}