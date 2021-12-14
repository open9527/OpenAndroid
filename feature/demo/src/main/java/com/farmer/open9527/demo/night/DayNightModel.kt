package com.farmer.open9527.demo.night

import androidx.annotation.StyleRes
import com.farmer.open9527.demo.R


enum class DayNightModel(@StyleRes val theme: Int) {
    DAY(R.style.TestDayTheme),
    NIGHT(R.style.TestNightTheme),
}