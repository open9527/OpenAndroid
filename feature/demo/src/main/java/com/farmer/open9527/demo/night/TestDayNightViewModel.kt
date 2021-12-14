package com.farmer.open9527.demo.night

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.farmer.open9527.common.event.SingleLiveEvent

class TestDayNightViewModel : ViewModel() {
    val valueTitle = ObservableField("DayNight")
    val valueTitleColor = ObservableInt()
    val eventChangeThemeClick = SingleLiveEvent<View>()

    fun changeTheme(view: View) {
        eventChangeThemeClick.postValue(view)
    }
}