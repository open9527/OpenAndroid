package com.farmer.open9527.demo.night

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SPUtils

import android.util.TypedValue
import android.view.View


class TestDayNightDelegate : TestIDayNightDelegate {
    private var mActivity: AppCompatActivity? = null

    constructor(activity: AppCompatActivity?) {
        mActivity = activity
        onClickChangeTheme()
    }

    override fun onDayTheme() {
        mActivity?.setTheme(DayNightModel.DAY.theme)
        save(false)
        refreshStatusBar(true)
        setGrayModel(0f)
    }

    override fun onNightTheme() {
        mActivity?.setTheme(DayNightModel.NIGHT.theme)
        save(true)
        refreshStatusBar(false)
        setGrayModel(1f)
    }

    override fun onClickChangeTheme() {
        if (getNight()) {
            onDayTheme()
        } else {
            onNightTheme()
        }

    }

    override fun refreshStatusBar(light: Boolean) {
        BarUtils.setStatusBarLightMode(mActivity!!, !light)
    }


    private fun save(night: Boolean) {
        SPUtils.getInstance().put("dayNight", night)
    }

    private fun getNight(): Boolean {
        return SPUtils.getInstance().getBoolean("dayNight", false)
    }

    fun getColor(resId: Int): Int {
        //R.attr.testTextColor
        val typedValue = TypedValue()
        val theme = mActivity?.theme
        theme?.resolveAttribute(resId, typedValue, true)
        return typedValue.data
    }

    private fun setGrayModel(saturation: Float) {
        val paint = Paint()
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(saturation)
        paint.setColorFilter(ColorMatrixColorFilter(colorMatrix))
        mActivity?.window?.decorView?.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }

}