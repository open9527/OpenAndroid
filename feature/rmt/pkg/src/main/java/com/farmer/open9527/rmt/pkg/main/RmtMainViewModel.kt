package com.farmer.open9527.rmt.pkg.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class RmtMainViewModel : ViewModel() {
    val valueTitle = ObservableField("RMT")

    val valueSplashPage = ObservableBoolean(true)

    val valueSplashTitle = ObservableField("SPLASH")

}