package com.farmer.open9527.rmt.export.binding.viewpage2

import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2


object ViewPager2BindingAdapter {
    @BindingAdapter(
        value = [
            "bindViewPager2Adapter",
            "bindViewPager2CurrentItem",
            "bindViewPager2SmoothScroll",
            "bindViewPager2OffscreenPageLimit",
            "bindViewPager2IsUserInputEnabled",
            "bindViewPager2PageChangeCallback",
        ],
        requireAll = false
    )
    @JvmStatic
    fun setBindingViewPager2(
        viewPager2: ViewPager2?,
        fragmentStateAdapter: FragmentStateAdapter,
        currentItem: Int,
        smoothScroll: Boolean,
        offscreenPageLimit: Int,
        isUserInputEnabled: Boolean,
        pageChangeCallback: ViewPager2.OnPageChangeCallback,
    ) {
        if (viewPager2 == null) return
        viewPager2.run {
            setOffscreenPageLimit(offscreenPageLimit)
            setUserInputEnabled(isUserInputEnabled)
            registerOnPageChangeCallback(pageChangeCallback)
            if (viewPager2.adapter == null) {
                adapter = fragmentStateAdapter
            }
            setCurrentItem(currentItem, smoothScroll)
        }

    }
}