package com.farmer.open9527.meeting.main.home

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.hjq.http.listener.OnHttpListener

class HomeViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("")
    val valueShowBack = ObservableInt(View.VISIBLE)


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}