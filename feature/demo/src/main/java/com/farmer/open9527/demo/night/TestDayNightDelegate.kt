package com.farmer.open9527.demo.night

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils

import android.util.TypedValue
import android.view.View
import com.farmer.open9527.demo.delegate.CommonDelegate
import com.tencent.mmkv.MMKV


class TestDayNightDelegate : CommonDelegate, TestIDayNightDelegate {

    private var mActivity: AppCompatActivity? = null
    private val mmkv: MMKV = MMKV.mmkvWithID("TestDayNightDelegate")


    constructor(activity: AppCompatActivity?) : super(activity) {
        this.mActivity = activity
        onClickChangeTheme()
    }

    override fun onDayTheme() {
        mActivity?.setTheme(DayNightModel.DAY.theme)
        save(false)
        refreshStatusBar(true)

    }

    override fun onNightTheme() {
        mActivity?.setTheme(DayNightModel.NIGHT.theme)
        save(true)
        refreshStatusBar(false)
    }

    override fun onClickChangeTheme() {
        if (getNight()) {
            onDayTheme()
        } else {
            onNightTheme()
        }
    }

    override fun refreshStatusBar(light: Boolean) {
        BarUtils.setStatusBarLightMode(mActivity!!, light)
    }

    override fun onBack() {
        mActivity?.finish()
    }


    fun changeGrayModel() {
        setGrayModel(if (getNight()) 1f else 0f)
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

    private fun save(night: Boolean) {
        mmkv.putBoolean("dayNight", night)
//        SPUtils.getInstance().put("dayNight", night)
    }

    private fun getNight(): Boolean {
//        return SPUtils.getInstance().getBoolean("dayNight", false)
        return mmkv.getBoolean("dayNight", false)
    }
}