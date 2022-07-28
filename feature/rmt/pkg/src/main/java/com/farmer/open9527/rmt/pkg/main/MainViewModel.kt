package com.farmer.open9527.rmt.pkg.main

import androidx.databinding.*
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.rmt.export.http.HttpData
import com.farmer.open9527.rmt.export.http.HttpRequestBody
import com.farmer.open9527.rmt.export.http.api.sys.UpgradeApi
import com.farmer.open9527.rmt.export.http.vo.sys.UpgradeVo
import com.farmer.open9527.rmt.pkg.R
import com.hjq.gson.factory.GsonFactory
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.request.PostRequest

class MainViewModel : ViewModel(), OnHttpListener<Any> {
    val valueTitle = ObservableField("RMT")
    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueNavigationAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    //    val valueNavigationAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    val valueViewPager2Adapter = ObservableField<FragmentStateAdapter>()
    val valueViewPager2OffscreenPageLimit = ObservableInt()
    val valueViewPager2CurrentItem = ObservableInt()
    val valueViewPager2SmoothScroll = ObservableBoolean(false)
    val valueViewPager2IsUserInputEnabled = ObservableBoolean(false)
    val valueViewPager2PageChangeCallback = ObservableField<ViewPager2.OnPageChangeCallback>()


    fun requestNavigation(
        iNavigationCell: NavigationCell.INavigationCell
    ) {
        valueListData.clear()
        valueListData.add(
            NavigationCell(
                "首页",
                R.drawable.main__home_off__icon,
                R.drawable.main__home_on__icon,
                0,
                iNavigationCell
            )
        )
        valueListData.add(
            NavigationCell(
                "视频",
                R.drawable.main__video_off__icon,
                R.drawable.main__video_on__icon,
                1,
                iNavigationCell
            )
        )
        valueListData.add(
            NavigationCell(
                "动态",
                R.drawable.main__moment__icon,
                R.drawable.main__moment__icon,
                2,
                iNavigationCell,
                R.layout.rmt__main_navigation_home__cell
            )
        )

        valueListData.add(
            NavigationCell(
                "音频",
                R.drawable.main__audio_off__icon,
                R.drawable.main__audio_on__icon,
                3,
                iNavigationCell
            )
        )
        valueListData.add(
            NavigationCell(
                "新闻",
                R.drawable.main__news_off__icon,
                R.drawable.main__news_on__icon,
                4,
                iNavigationCell
            )
        )
    }


    fun requestUpgrade(request: PostRequest) {
        request.api(UpgradeApi())
            .body(
                HttpRequestBody(
                    UpgradeApi.getRequestBody(
                        DeviceUtils.getUniqueDeviceId(),
                        ScreenUtils.getAppScreenWidth(),
                        ScreenUtils.getAppScreenHeight(),
                        DeviceUtils.getSDKVersionName(),
                        AppUtils.getAppVersionName()
                    )
                )
            )
            .request(object : HttpCallback<HttpData<UpgradeVo?>>(this) {
                override fun onSucceed(data: HttpData<UpgradeVo?>) {
                    LogUtils.i(TAG, "data:" + GsonFactory.getSingletonGson().toJson(data.getData()))
                }
            })
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }

}