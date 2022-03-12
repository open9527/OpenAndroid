package com.farmer.open9527.meeting.main.home

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.hjq.http.listener.OnHttpListener

class HomeViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("会议")
    val valueShowBack = ObservableInt(View.INVISIBLE)


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}