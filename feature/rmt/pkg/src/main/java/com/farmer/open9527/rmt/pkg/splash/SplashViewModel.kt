package com.farmer.open9527.rmt.pkg.splash

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.*
import com.farmer.open9527.common.event.SingleLiveEvent
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.HttpRequestBody
import com.farmer.open9527.rmt.export.http.api.sys.BillboardApi
import com.farmer.open9527.rmt.export.http.vo.sys.BillboardVo
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.PostRequest
import com.zhpan.bannerview.BaseBannerAdapter
import okhttp3.Call

class SplashViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("Splash")


    val billboardEvent = SingleLiveEvent<List<BillboardVo>>()
    val valueBannerVisibility = ObservableInt(View.INVISIBLE)
    val valueBillboardData = ObservableField<List<BillboardVo>>()
    val valueBannerAdapter = ObservableField<BaseBannerAdapter<BillboardVo>>()
    val valueBannerData = ObservableField<List<BillboardVo>>()


    fun requestBillboard(request: PostRequest) {
        request.api(BillboardApi())
            .body(
                HttpRequestBody(
                    BillboardApi.getRequestBody(
                        DeviceUtils.getUniqueDeviceId(),
                        ScreenUtils.getAppScreenWidth(),
                        ScreenUtils.getAppScreenHeight(),
                        DeviceUtils.getSDKVersionName(),
                        AppUtils.getAppVersionName()
                    )
                )
            )
            .request(object : HttpCallback<HttpData<List<BillboardVo>?>>(this) {
                override fun onSucceed(data: HttpData<List<BillboardVo>?>) {
                    valueBillboardData.set(data.getData())
                    LogUtils.i(TAG, "requestBillboard :" + GsonUtils.toJson(data.getData()))
                }

                override fun onEnd(call: Call?) {
                    billboardEvent.postValue(valueBillboardData.get())
                }
            })
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }

}