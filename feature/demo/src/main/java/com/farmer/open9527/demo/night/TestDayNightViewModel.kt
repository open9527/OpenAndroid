package com.farmer.open9527.demo.night

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.farmer.open9527.common.event.SingleLiveEvent

class TestDayNightViewModel : ViewModel() {
    val valueTitle = ObservableField("DayNight")
    val valueTitleColor = ObservableInt()

    private var backClickEvent: SingleLiveEvent<View>? = null
    private var changeThemeClickEvent: SingleLiveEvent<View>? = null
    private var changeGrayModelClickEvent: SingleLiveEvent<View>? = null


    fun getBackClickEvent(): SingleLiveEvent<View>? {
        if (backClickEvent == null) {
            backClickEvent = getViewClickEvent()
        }
        return backClickEvent
    }

    fun getChangeThemeEvent(): SingleLiveEvent<View>? {
        if (changeThemeClickEvent == null) {
            changeThemeClickEvent = getViewClickEvent()
        }
        return changeThemeClickEvent
    }

    fun getChangeGrayModelClickEvent(): SingleLiveEvent<View>? {
        if (changeGrayModelClickEvent == null) {
            changeGrayModelClickEvent = getViewClickEvent()
        }
        return changeGrayModelClickEvent
    }


    fun back(view: View) {
        getBackClickEvent()?.postValue(view)
    }

    fun changeTheme(view: View) {
        getChangeThemeEvent()?.postValue(view)
    }

    fun changeGrayModel(view: View) {
        getChangeGrayModelClickEvent()?.postValue(view)
    }

    private fun getViewClickEvent(): SingleLiveEvent<View> {
        return SingleLiveEvent<View>()
    }
}