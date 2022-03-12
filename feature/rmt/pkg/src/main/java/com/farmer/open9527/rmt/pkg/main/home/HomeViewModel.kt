package com.farmer.open9527.rmt.pkg.main.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.LogUtils
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.api.news.panel.HomePanelApi
import com.farmer.open9527.rmt.export.http.vo.channel.ChannelVo
import com.hjq.gson.factory.GsonFactory
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.PostRequest

class HomeViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("home")


    fun requestHomePanelApi(request: PostRequest) {
        request.api(HomePanelApi())
            .request(object : HttpCallback<HttpData<List<ChannelVo>?>>(this) {
                override fun onSucceed(data: HttpData<List<ChannelVo>?>) {
                    LogUtils.i(TAG, "data:" + GsonFactory.getSingletonGson().toJson(data.getData()))
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