package com.farmer.open9527.meeting.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.farmer.open9527.common.event.SingleLiveEvent
import com.farmer.open9527.rmt.export.http.vo.sys.BillboardVo
import com.hjq.http.listener.OnHttpListener

class SplashViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("Splash")


    val billboardEvent = SingleLiveEvent<List<BillboardVo>>()


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }

}