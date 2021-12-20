package com.farmer.open9527.demo.delegate

import androidx.appcompat.app.AppCompatActivity

open class CommonDelegate : CommonLifecycleDelegate ,ICommonDelegate{

    constructor(activity: AppCompatActivity?) : super(activity)

    constructor() : super(null)

    override fun onBack() {

    }

}