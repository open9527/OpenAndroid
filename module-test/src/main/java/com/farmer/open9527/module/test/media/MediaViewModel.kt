package com.farmer.open9527.module.test.media

import android.content.Context
import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 *@author   open_9527
 *Create at 2021/10/21
 **/
class MediaViewModel : ViewModel() {

    val valueTitle = ObservableField("MediaActivity")
    val valueImageUri = ObservableField<Uri>()
    val albumLiveData = MutableLiveData<List<String>>()

    fun getMediaAlbum(context: Context) {

    }


}