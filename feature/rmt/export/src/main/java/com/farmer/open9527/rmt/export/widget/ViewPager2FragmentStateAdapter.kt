package com.farmer.open9527.rmt.export.widget

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


 class ViewPager2FragmentStateAdapter(
    activity: AppCompatActivity,
    private val fragmentList: List<Fragment>
) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}