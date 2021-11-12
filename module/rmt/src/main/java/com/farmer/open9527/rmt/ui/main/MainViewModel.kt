package com.farmer.open9527.rmt.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmer.open9527.flow.observer.StateMutableLiveData
import com.farmer.open9527.rmt.net.api.common.CommonRepository
import com.farmer.open9527.rmt.net.api.home.HomeRepository
import kotlinx.coroutines.launch


/**
 *@author   open_9527
 *Create at 2021/11/11
 **/
class MainViewModel : ViewModel() {


    private val homeRepository by lazy { HomeRepository() }

    val homePanelListLiveData = StateMutableLiveData<Any?>()

    fun requestHomePanelList() {
        viewModelScope.launch {
            homePanelListLiveData.value = homeRepository.requestHomePanelList()
        }
    }

}