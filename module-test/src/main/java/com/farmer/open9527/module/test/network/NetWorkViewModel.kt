package com.farmer.open9527.module.test.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.farmer.open9527.network.StateLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


/**
 *@author   open_9527
 *Create at 2021/10/20
 **/
class NetWorkViewModel : ViewModel() {

    private val repository by lazy { WxArticleRepository() }

    val userLiveData = StateLiveData<Any?>()
    val wxArticleLiveData = StateLiveData<Any?>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userLiveData.value = repository.login(username, password)
        }

    }

    fun fetchWxArticleFromNet() {
        viewModelScope.launch {
            wxArticleLiveData.value = repository.fetchWxArticleFromNet()
        }
    }

}